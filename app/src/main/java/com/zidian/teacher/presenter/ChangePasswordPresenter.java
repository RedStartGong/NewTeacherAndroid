package com.zidian.teacher.presenter;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.ChangePasswordContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * 修改密码 presenter
 * Created by GongCheng on 2017/4/6.
 */

public class ChangePasswordPresenter extends RxPresenter<ChangePasswordContract.View>
        implements ChangePasswordContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public ChangePasswordPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void changePassword(@NonNull final String password, @NonNull final String password1,
                               @NonNull final String password2) {
        view.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Subscription subscription = dataManager.changePassword(SharedPreferencesUtils.getTeacherId(),
                        password, password1, password2)
                        .compose(RxUtils.<NoDataResult>rxSchedulerIo())
                        .map(new Func1<NoDataResult, NoDataResult>() {
                            @Override
                            public NoDataResult call(NoDataResult noDataResult) {
                                if (noDataResult.getCode() != 200) {
                                    throw new ApiException(noDataResult.getMessage());
                                } else {
                                    return noDataResult;
                                }
                            }
                        })
                        .subscribe(new Subscriber<NoDataResult>() {

                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showError(e);
                            }

                            @Override
                            public void onNext(NoDataResult noDataResult) {
                                view.showSuccess();
                            }
                        });
                addSubscribe(subscription);
            }
        }, 1000);

    }
}
