package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.EvaluateCourse;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.ApplyToEvaContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * 申请评价他人 presenter
 * Created by GongCheng on 2017/4/19.
 */

public class ApplyToEvaPresenter extends RxPresenter<ApplyToEvaContract.View>
        implements ApplyToEvaContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public ApplyToEvaPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getEvaluateCourses() {
        Subscription subscription = dataManager.getEvaluateCourses(SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<EvaluateCourse>>>rxSchedulerIo())
                .compose(RxUtils.<List<EvaluateCourse>>handleHttpResult())
                .retry()
                .subscribe(new Subscriber<List<EvaluateCourse>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<EvaluateCourse> courses) {
                        view.showEvaluateCourses(courses);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void apply(String requestedPerson, String teacherCollege, String courseId,
                      String courseName, String teachingCalendar, String classroom,
                      String requestExplain) {
        Subscription subscription = dataManager.inviteOrApply(SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getTeacherName(), requestedPerson, "0", teacherCollege,
                courseId, courseName, teachingCalendar, classroom, requestExplain, SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
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
