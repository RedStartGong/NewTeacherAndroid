package com.zidian.teacher.presenter;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.model.entity.remote.CourseTime;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.ScheduleContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 课程表 presenter
 * Created by GongCheng on 2017/4/7.
 */

public class SchedulePresenter extends RxPresenter<ScheduleContract.View> implements ScheduleContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public SchedulePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getSchedule(@NonNull int week) {
        Subscription subscription = dataManager.getCourses(SharedPreferencesUtils.getTeacherId(), week)
                .compose(RxUtils.<HttpResult<List<Course>>>rxSchedulerIo())
                .compose(RxUtils.<List<Course>>handleHttpResult())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        view.showSchedule(courses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showError(throwable);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getCourseTime(@NonNull int week) {
        Subscription subscription = dataManager.getCourseTime(
                SharedPreferencesUtils.getTeacherId(), week)
                .compose(RxUtils.<HttpResult<CourseTime>>rxSchedulerIo())
                .compose(RxUtils.<CourseTime>handleHttpResult())
                .subscribe(new Subscriber<CourseTime>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(CourseTime courseTime) {
                        view.showCourseTime(courseTime);
                    }
                });
        addSubscribe(subscription);
    }
}
