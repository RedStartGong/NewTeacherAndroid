package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.CustomEva;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.CustomEvaContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * 我的评价-自定义评价 presenter
 * Created by GongCheng on 2017/4/28.
 */

public class CustomEvaPresenter extends RxPresenter<CustomEvaContract.View> implements CustomEvaContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public CustomEvaPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getCustomEva(String startRow) {
        Subscription subscription = dataManager.customEva(startRow, "10",
                SharedPreferencesUtils.getUserName(), "教师",
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<CustomEva>>rxSchedulerIo())
                .compose(RxUtils.<CustomEva>handleHttpResult())
                .subscribe(new Subscriber<CustomEva>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(CustomEva customEva) {
                        if (customEva.getList() != null && customEva.getList().size() != 0) {
                            view.showCustomEva(customEva);
                        } else {
                            view.showEmpty();
                        }
                    }
                });
        addSubscribe(subscription);
    }
}
