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
 * 登录 presenter
 * Created by GongCheng on 2017/3/20.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void login(@NonNull final String username, @NonNull final String password, @NonNull final int schoolId) {
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
                        SharedPreferencesUtils.setUsername(username);
                        SharedPreferencesUtils.setPassword(password);
                        SharedPreferencesUtils.setSchoolId(schoolId);
                        SharedPreferencesUtils.setTeacherId(loginResult.getTeacherId());
                        SharedPreferencesUtils.setIsLogin(true);
                        SharedPreferencesUtils.setTeacherType(loginResult.getTeacherType());
                        SharedPreferencesUtils.setTeacherName(loginResult.getTeacherName());
                        view.showSuccess();
                    }
                });
        addSubscribe(subscription);
    }
}
