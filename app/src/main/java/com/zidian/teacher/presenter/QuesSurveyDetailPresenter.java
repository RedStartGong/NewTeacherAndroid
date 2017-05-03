package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.entity.remote.QuesSurveyDetail;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.QuesSurveyDetailContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class QuesSurveyDetailPresenter extends RxPresenter<QuesSurveyDetailContract.View>
        implements QuesSurveyDetailContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public QuesSurveyDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getQuesDetail(String questionnaireId) {
        Subscription subscription = dataManager.quesSurveyDetail(questionnaireId,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<QuesSurveyDetail>>rxSchedulerIo())
                .compose(RxUtils.<QuesSurveyDetail>handleHttpResult())
                .subscribe(new Action1<QuesSurveyDetail>() {
                    @Override
                    public void call(QuesSurveyDetail quesSurveyDetail) {
                        view.showQuesDetail(quesSurveyDetail);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showError(throwable);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void quesSubmit(String quesSubmit) {
        Subscription subscription = dataManager.quesSubmit(quesSubmit,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NoDataResult>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        view.showSubmitLoading();
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
                        view.showSubmitSuccess();
                    }
                });
        addSubscribe(subscription);
    }
}
