package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.QuestionnaireAddContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by GongCheng on 2017/5/5.
 */

public class QuestionnaireAddPresenter extends RxPresenter<QuestionnaireAddContract.View>
        implements QuestionnaireAddContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public QuestionnaireAddPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void addQuestionnaire(String classList, String quesTitle, String quesRemark, String quesItems) {
        Subscription subscription = dataManager.addQuestionnaire(classList, quesTitle, quesRemark,
                quesItems, SharedPreferencesUtils.getTeacherId())
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
                    public void onNext(NoDataResult noDataResult) {
                        view.showSuccess();
                    }
                });
        addSubscribe(subscription);
    }
}
