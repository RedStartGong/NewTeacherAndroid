package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.CoursePlan;
import com.zidian.teacher.model.entity.remote.EvaCourse;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.InviteCourseResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.InviteContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 邀请评价 presetner
 * Created by GongCheng on 2017/4/14.
 */

public class InvitePresenter extends RxPresenter<InviteContract.View> implements InviteContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public InvitePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getCourses() {
        Subscription subscription = dataManager.getEvaCourses(
                SharedPreferencesUtils.getTeacherId(), SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<HttpResult<List<EvaCourse>>>rxSchedulerIo())
                .compose(RxUtils.<List<EvaCourse>>handleHttpResult())
                .subscribe(new Subscriber<List<EvaCourse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<EvaCourse> evaCourses) {
                        view.showEvaCourses(evaCourses);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getCoursePlans(int courseId) {
        Subscription subscription = dataManager.getCoursePlans(
                SharedPreferencesUtils.getTeacherId(), SharedPreferencesUtils.getTeacherId(), courseId)
                .compose(RxUtils.<HttpResult<List<CoursePlan>>>rxSchedulerIo())
                .compose(RxUtils.<List<CoursePlan>>handleHttpResult())
                .subscribe(new Subscriber<List<CoursePlan>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<CoursePlan> coursePlans) {
                        view.showCoursePlans(coursePlans);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void invite(String requestedPerson, String teacherCollege, int courseId, String courseName,
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
