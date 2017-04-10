package com.zidian.teacher.presenter;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.Class;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.AttendanceStatisticsContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by GongCheng on 2017/4/9.
 */

public class AttendanceStatisticsPresenter extends RxPresenter<AttendanceStatisticsContract.View>
        implements AttendanceStatisticsContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public AttendanceStatisticsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getClasses( String courseId) {
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
    public void getAttendanceStatistics(@NonNull String courseId, @NonNull String className) {
        Subscription subscription = dataManager.getAttendanceStatistics(courseId, className,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<AttendanceStatistics>>>rxSchedulerIo())
                .compose(RxUtils.<List<AttendanceStatistics>>handleHttpResult())
                .subscribe(new Subscriber<List<AttendanceStatistics>>() {
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
                    public void onNext(List<AttendanceStatistics> attendanceStatistics) {
                        view.showSuccess();
                        view.showStatistics(attendanceStatistics);
                    }
                });
        addSubscribe(subscription);
    }
}
