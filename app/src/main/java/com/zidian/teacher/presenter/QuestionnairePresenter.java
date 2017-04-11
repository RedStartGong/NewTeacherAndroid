package com.zidian.teacher.presenter;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.Questionnaire;
import com.zidian.teacher.presenter.contract.QuestionnaireContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by GongCheng on 2017/4/1.
 */

public class QuestionnairePresenter extends RxPresenter<QuestionnaireContract.View>
        implements QuestionnaireContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public QuestionnairePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getQuestionnaires(@NonNull String startRow) {
        Subscription subscription = dataManager.getQuestionnaire(startRow, "10",
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<Questionnaire>>rxSchedulerIo())
                .compose(RxUtils.<Questionnaire>handleHttpResult())
                .subscribe(new Subscriber<Questionnaire>() {
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
                    public void onNext(Questionnaire questionnaire) {
                        view.showEmpty();
                    }
                });
        addSubscribe(subscription);
    }
}
