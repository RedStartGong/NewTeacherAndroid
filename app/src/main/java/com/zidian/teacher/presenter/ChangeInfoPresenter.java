package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.ChangeInfoContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * 修改个人信息 presenter
 * Created by GongCheng on 2017/4/11.
 */

public class ChangeInfoPresenter extends RxPresenter<ChangeInfoContract.View> implements ChangeInfoContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public ChangeInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void changeUserInfo(RequestBody teacherId, RequestBody aliasName, RequestBody phone,
                               RequestBody signName, RequestBody birthday, RequestBody sex,
                               MultipartBody.Part iconUrl) {
        Subscription subscription = dataManager.changeUserInfo(teacherId, aliasName, phone,
                signName, birthday, sex, iconUrl)
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
                    public void onStart() {
                        super.onStart();
                        view.showLoading("上传中...");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(NoDataResult noDataResult) {
                        view.showSuccess(noDataResult.getMessage());
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void changeUserInfoNoImg(RequestBody teacherId, RequestBody aliasName,
                                    RequestBody phone, RequestBody signName,
                                    RequestBody birthday, RequestBody sex) {

    }
}
