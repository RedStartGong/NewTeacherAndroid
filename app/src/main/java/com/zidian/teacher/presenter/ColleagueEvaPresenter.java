package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.ColleagueEva;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.ColleagueEvaContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * 我的评价-同行评价 presenter
 * Created by GongCheng on 2017/4/28.
 */

public class ColleagueEvaPresenter extends RxPresenter<ColleagueEvaContract.View>
        implements ColleagueEvaContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public ColleagueEvaPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getColleagueEva() {
        Subscription subscription = dataManager.colleagueEva(SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<HttpResult<List<ColleagueEva>>>rxSchedulerIo())
                .compose(RxUtils.<List<ColleagueEva>>handleHttpResult())
                .subscribe(new Subscriber<List<ColleagueEva>>() {
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
                    public void onNext(List<ColleagueEva> colleagueEvas) {
                        view.showColleagueEva(colleagueEvas);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getSupervisorEva() {
        Subscription subscription = dataManager.supervisorEva(SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<HttpResult<List<ColleagueEva>>>rxSchedulerIo())
                .compose(RxUtils.<List<ColleagueEva>>handleHttpResult())
                .subscribe(new Subscriber<List<ColleagueEva>>() {
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
                    public void onNext(List<ColleagueEva> colleagueEvas) {
                        view.showColleagueEva(colleagueEvas);
                    }
                });
        addSubscribe(subscription);
    }
}
