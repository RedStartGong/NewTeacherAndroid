package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.SelectClass;
import com.zidian.teacher.presenter.contract.SelectClassContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by GongCheng on 2017/5/8.
 */

public class SelectClassPresenter extends RxPresenter<SelectClassContract.View>
        implements SelectClassContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public SelectClassPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getAllClasses() {
        Subscription subscription = dataManager.getAllClasses(SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<SelectClass>rxSchedulerIo())
                .subscribe(new Action1<SelectClass>() {
                    @Override
                    public void call(SelectClass selectClass) {
                        view.showClasses(selectClass.getDate());
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
