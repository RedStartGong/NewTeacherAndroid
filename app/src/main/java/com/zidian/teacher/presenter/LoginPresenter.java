package com.zidian.teacher.presenter;


import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.LoginResult;
import com.zidian.teacher.model.entity.remote.School;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.LoginContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by GongCheng on 2017/3/20.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getSchools() {
        Subscription subscription = dataManager.getSchools()
                .compose(RxUtils.<HttpResult<List<School>>>rxSchedulerIo())
                .compose(RxUtils.<List<School>>handleHttpResult())
                .subscribe(new Action1<List<School>>() {
                    @Override
                    public void call(List<School> schools) {
                        view.showSchool(schools);
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
    public void login(@NonNull String username, @NonNull final String password, @NonNull final String schoolId) {
        Subscription subscription = dataManager.login(username, password, schoolId)
                .compose(RxUtils.<LoginResult>rxSchedulerIo())
                .map(new Func1<LoginResult, LoginResult>() {
                    @Override
                    public LoginResult call(LoginResult loginResult) {
                        if (loginResult.getCode() == 200) {
                            return loginResult;
                        } else {
                            throw new ApiException(loginResult.getMessage());
                        }
                    }
                })
                .subscribe(new Subscriber<LoginResult>() {
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
                    public void onNext(LoginResult loginResult) {
                        SharedPreferencesUtils.setToken(loginResult.getToken());
                        SharedPreferencesUtils.setUsername(loginResult.getTeacherId());
                        SharedPreferencesUtils.setPassword(password);
                        SharedPreferencesUtils.setSchoolId(schoolId);
                        view.showSuccess();
                    }
                });
        addSubscribe(subscription);
    }
}
