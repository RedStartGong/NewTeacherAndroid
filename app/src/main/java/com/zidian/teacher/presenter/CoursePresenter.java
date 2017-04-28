package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.CourseContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 课程表 presenter
 * Created by GongCheng on 2017/4/7.
 */

public class CoursePresenter extends RxPresenter<CourseContract.View> implements CourseContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public CoursePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getCourse() {
        Subscription subscription = dataManager.getCourses(SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<Course>>>rxSchedulerIo())
                .compose(RxUtils.<List<Course>>handleHttpResult())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        view.showCourse(courses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showError(throwable);
                    }
                });
        addSubscribe(subscription);
    }
}
