package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.CheckSupervisorEvaContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by GongCheng on 2017/4/25.
 */

public class CheckSupervisorEvaPresenter extends RxPresenter<CheckSupervisorEvaContract.View>
        implements CheckSupervisorEvaContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public CheckSupervisorEvaPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getEvaTags(String recordId) {
        Subscription subscription = dataManager.checkSupervisorEva(recordId, SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<CheckSupervisorEva>>rxSchedulerIo())
                .compose(RxUtils.<CheckSupervisorEva>handleHttpResult())
                .subscribe(new Subscriber<CheckSupervisorEva>() {
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
                    public void onNext(CheckSupervisorEva checkSupervisorEva) {
                        view.showEvaTags(checkSupervisorEva);
                    }
                });
        addSubscribe(subscription);
    }

}
