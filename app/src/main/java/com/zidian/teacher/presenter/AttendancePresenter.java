package com.zidian.teacher.presenter;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.Class;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.AttendanceContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by GongCheng on 2017/4/8.
 */

public class AttendancePresenter extends RxPresenter<AttendanceContract.View> implements AttendanceContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public AttendancePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getClasses(@NonNull String courseId) {
        Subscription subscription = dataManager.getClasses(courseId, SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<Class>>>rxSchedulerIo())
                .compose(RxUtils.<List<Class>>handleHttpResult())
                .retry()
                .subscribe(new Action1<List<Class>>() {
                    @Override
                    public void call(List<Class> classes) {
                        view.showClasses(classes);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void setAttendance(@NonNull String student, @NonNull String courseId, @NonNull String courseWeeklyId) {
        Subscription subscription = dataManager.setAttendance(student, courseId, courseWeeklyId,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
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
                }).subscribe(new Subscriber<NoDataResult>() {
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
