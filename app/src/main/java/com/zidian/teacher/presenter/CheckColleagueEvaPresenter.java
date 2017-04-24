package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.CheckColleagueEva;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.CheckColleagueEvaContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by GongCheng on 2017/4/24.
 */

public class CheckColleagueEvaPresenter extends RxPresenter<CheckColleagueEvaContract.View>
        implements CheckColleagueEvaContract.Presenter{
    private final DataManager dataManager;

    @Inject
    public CheckColleagueEvaPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void checkColleagueEva(String recordId) {
        Subscription subscription = dataManager.checkColleagueEva(recordId,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<CheckColleagueEva>>rxSchedulerIo())
                .compose(RxUtils.<CheckColleagueEva>handleHttpResult())
                .subscribe(new Subscriber<CheckColleagueEva>() {
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
                    public void onNext(CheckColleagueEva checkColleagueEva) {
                        view.showEvaTags(checkColleagueEva);
                    }
                });
        addSubscribe(subscription);

    }
}
