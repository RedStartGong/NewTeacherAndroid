package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.MyQuestionnaire;
import com.zidian.teacher.presenter.contract.MyQuestionnaireListContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

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
    public void getMyQues() {
        Subscription subscription = dataManager.myQuesList(SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<HttpResult<List<MyQuestionnaire>>>rxSchedulerIo())
                .compose(RxUtils.<List<MyQuestionnaire>>handleHttpResult())
                .subscribe(new Subscriber<List<MyQuestionnaire>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<MyQuestionnaire> myQuestionnaires) {
                        view.showMyQues(myQuestionnaires);
                    }
                });

        addSubscribe(subscription);
    }
}
