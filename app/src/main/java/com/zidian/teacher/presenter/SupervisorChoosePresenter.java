package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.EvaluateCourse;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.SupervisorChooseContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by GongCheng on 2017/4/18.
 */

public class SupervisorChoosePresenter extends RxPresenter<SupervisorChooseContract.View>
        implements SupervisorChooseContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public SupervisorChoosePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getEvaluateCourses() {
        Subscription subscription = dataManager.getEvaluateCourses(SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<EvaluateCourse>>>rxSchedulerIo())
                .compose(RxUtils.<List<EvaluateCourse>>handleHttpResult())
                .subscribe(new Subscriber<List<EvaluateCourse>>() {
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
                    public void onNext(List<EvaluateCourse> courses) {
                        view.showEvaluateCourses(courses);
                    }
                });
        addSubscribe(subscription);
    }
}
