package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.EvaluateTag;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.CheckColleagueEvaContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * 查看同行评价 presenter
 * Created by GongCheng on 2017/4/24.
 */

public class CheckColleagueEvaPresenter extends RxPresenter<CheckColleagueEvaContract.View>
        implements CheckColleagueEvaContract.Presenter{
    private final DataManager dataManager;

    @Inject
    public CheckColleagueEvaPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getEvaluateTags(int requestEvalMessageId) {
        Subscription subscription = dataManager.getEvaluateTag(requestEvalMessageId, SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<HttpResult<EvaluateTag>>rxSchedulerIo())
                .compose(RxUtils.<EvaluateTag>handleHttpResult())
                .subscribe(new Subscriber<EvaluateTag>() {
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
                    public void onNext(EvaluateTag evaluateTag) {
                        view.showEvaTag(evaluateTag);
                    }
                });
        addSubscribe(subscription);
    }
}
