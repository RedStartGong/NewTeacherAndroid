package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.MyQuesList;
import com.zidian.teacher.presenter.contract.MyQuestionnaireListContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class MyQuestionnaireListPresenter extends RxPresenter<MyQuestionnaireListContract.View>
        implements MyQuestionnaireListContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public MyQuestionnaireListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getMyQues(String startRow) {
        Subscription subscription = dataManager.myQuesList(startRow, "10",
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<MyQuesList>>rxSchedulerIo())
                .compose(RxUtils.<MyQuesList>handleHttpResult())
                .subscribe(new Action1<MyQuesList>() {
                    @Override
                    public void call(MyQuesList myQuesList) {
                        if (myQuesList.getList().isEmpty()) {
                            view.showEmpty();
                        } else {
                            view.showMyQues(myQuesList);
                        }
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
