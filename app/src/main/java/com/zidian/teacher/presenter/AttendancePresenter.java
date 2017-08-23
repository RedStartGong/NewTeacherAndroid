package com.zidian.teacher.presenter;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.StudentClass;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.AttendanceContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 考勤 presenter
 * Created by GongCheng on 2017/4/8.
 */

public class AttendancePresenter extends RxPresenter<AttendanceContract.View> implements AttendanceContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public AttendancePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getClasses(@NonNull int courseId) {
        Subscription subscription = dataManager.getClasses(courseId)
                .compose(RxUtils.<HttpResult<List<StudentClass>>>rxSchedulerIo())
                .compose(RxUtils.<List<StudentClass>>handleHttpResult())
                .retry(3)
                .subscribe(new Action1<List<StudentClass>>() {
                    @Override
                    public void call(List<StudentClass> classes) {
                        view.showClasses(classes);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getAttendanceStudents(@NonNull String courseWeeklyId, @NonNull int courseId, @NonNull String className) {
        Subscription subscription = dataManager.getAttendanceStudent(courseWeeklyId, courseId, className,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<AttendanceStudent>rxSchedulerIo())
                .flatMap(new Func1<AttendanceStudent, Observable<List<AttendanceStudent.DataBean>>>() {
                    @Override
                    public Observable<List<AttendanceStudent.DataBean>> call(final AttendanceStudent attendanceStudent) {
                        if (attendanceStudent.getCode() != 200) {
                            return Observable.error(new ApiException(attendanceStudent.getMessage()));
                        } else {
                            return Observable.create(new Observable.OnSubscribe<List<AttendanceStudent.DataBean>>() {
                                @Override
                                public void call(Subscriber<? super List<AttendanceStudent.DataBean>> subscriber) {
                                    try {
                                        subscriber.onNext(attendanceStudent.getData());
                                        subscriber.onCompleted();
                                    } catch (Throwable throwable) {
                                        subscriber.onError(throwable);
                                    }

                                }
                            });
                        }
                    }
                }).subscribe(new Subscriber<List<AttendanceStudent.DataBean>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        view.showLoadingStudents();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showLoadStudentsError(e);
                    }

                    @Override
                    public void onNext(List<AttendanceStudent.DataBean> dataBeen) {
                        view.showStudents(dataBeen);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void setAttendance(@NonNull String student, @NonNull int courseId, @NonNull String courseWeeklyId) {
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
