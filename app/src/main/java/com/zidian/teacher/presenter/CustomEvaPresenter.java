package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.CustomEva;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.CustomEvaContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

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
    public void getCustomEva(int startRow) {
        Subscription subscription = dataManager.customEva(startRow, 10, SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<HttpResult<List<CustomEva>>>rxSchedulerIo())
                .compose(RxUtils.<List<CustomEva>>handleHttpResult())
                .subscribe(new Subscriber<List<CustomEva>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<CustomEva> customEvas) {
                        view.showCustomEva(customEvas);
                    }
                });
        addSubscribe(subscription);
    }
}
