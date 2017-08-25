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

import rx.Subscriber;
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
        Subscription subscription = dataManager.getAllClasses(SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<HttpResult<List<SelectClass>>>rxSchedulerIo())
                .compose(RxUtils.<List<SelectClass>>handleHttpResult())
                .subscribe(new Subscriber<List<SelectClass>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<SelectClass> selectClasses) {
                        view.showClasses(selectClasses);

                    }
                });
        addSubscribe(subscription);
    }
}
