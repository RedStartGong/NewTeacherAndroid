package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.InviteCourseResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.InviteContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by GongCheng on 2017/4/14.
 */

public class InvitePresenter extends RxPresenter<InviteContract.View> implements InviteContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public InvitePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getInviteCourses() {
        Subscription subscription = dataManager.getInviteCourses(SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<InviteCourseResult>rxSchedulerIo())
                .retry()
                .map(new Func1<InviteCourseResult, List<InviteCourseResult.CourseBean>>() {
                    @Override
                    public List<InviteCourseResult.CourseBean> call(InviteCourseResult inviteCourseResult) {
                        return inviteCourseResult.getCourse();
                    }
                })
                .subscribe(new Action1<List<InviteCourseResult.CourseBean>>() {
                    @Override
                    public void call(List<InviteCourseResult.CourseBean> courses) {
                        view.showInviteCourses(courses);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void invite(String requestedPerson, String teacherCollege, String courseId, String courseName,
                       String teachingCalendar, String classroom, String requestExplain) {
        Subscription subscription = dataManager.inviteOrApply(SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getTeacherName(), requestedPerson, "1", teacherCollege,
                courseId, courseName, teachingCalendar, classroom, requestExplain,
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<NoDataResult>rxSchedulerIo())
                .map(new Func1<NoDataResult, NoDataResult>() {
                    @Override
                    public NoDataResult call(NoDataResult noDataResult) {
                        if (noDataResult.getCode() != 200) {
                            throw new ApiException(noDataResult.getMessage());
                        } else {
                            return noDataResult;
                        }
                    }
                })
                .subscribe(new Subscriber<NoDataResult>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        view.showLoading();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(NoDataResult noDataResult) {
                        view.showSuccess();
                    }
                });
        addSubscribe(subscription);
    }
}