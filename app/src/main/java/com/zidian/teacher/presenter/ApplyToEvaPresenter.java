package com.zidian.teacher.presenter;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.College;
import com.zidian.teacher.model.entity.remote.CoursePlan;
import com.zidian.teacher.model.entity.remote.EvaCourse;
import com.zidian.teacher.model.entity.remote.EvaTeacher;
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
    public void getColleges() {
        Subscription subscription = dataManager.getColleges(SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<HttpResult<List<College>>>rxSchedulerIo())
                .compose(RxUtils.<List<College>>handleHttpResult())
                .subscribe(new Subscriber<List<College>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<College> colleges) {
                        view.showColleges(colleges);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getEvaTeachers(@NonNull int collegeId) {
        Subscription subscription = dataManager.getTeachers(SharedPreferencesUtils.getTeacherId(), collegeId)
                .compose(RxUtils.<HttpResult<List<EvaTeacher>>>rxSchedulerIo())
                .compose(RxUtils.<List<EvaTeacher>>handleHttpResult())
                .subscribe(new Subscriber<List<EvaTeacher>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<EvaTeacher> evaTeachers) {
                        view.showEvaTeachers(evaTeachers);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getEvaCourses(int evaTeacherId) {
        Subscription subscription = dataManager.getEvaCourses(SharedPreferencesUtils.getTeacherId(), evaTeacherId)
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
    public void getEvaCoursePlans(@NonNull int evaTeacherId, @NonNull int courseId) {
        Subscription subscription = dataManager.getCoursePlans(SharedPreferencesUtils.getTeacherId(),
                evaTeacherId, courseId)
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
                    public void onNext(List<CoursePlan> coursePlen) {
                        view.showEvaCoursePlans(coursePlen);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void apply(@NonNull final String toTeacherId, @NonNull final String requestMessage,
                      @NonNull final int evaluateType, @NonNull final int coursePlanId) {
        view.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Subscription subscription = dataManager.inviteOrApply(SharedPreferencesUtils.getTeacherId(),
                        toTeacherId, 0, requestMessage, evaluateType, coursePlanId)
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
        }, 1000);

    }
}
