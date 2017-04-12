package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.PersonInfo;
import com.zidian.teacher.presenter.contract.PersonInfoContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by GongCheng on 2017/4/6.
 */

public class PersonInfoPresenter extends RxPresenter<PersonInfoContract.View> implements PersonInfoContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public PersonInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getPersonInfo() {
        Subscription subscription = dataManager.getPersonInfo(SharedPreferencesUtils.getUserName(),
                String.valueOf(SharedPreferencesUtils.getTeacherType()), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<PersonInfo>>rxSchedulerIo())
                .compose(RxUtils.<PersonInfo>handleHttpResult())
                .subscribe(new Action1<PersonInfo>() {
                    @Override
                    public void call(PersonInfo personInfo) {
                        view.showInfo(personInfo);
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
