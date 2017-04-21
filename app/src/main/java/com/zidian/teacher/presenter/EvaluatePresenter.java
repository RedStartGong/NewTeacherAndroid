package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.EvaluateTag;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.EvaluateContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by GongCheng on 2017/4/20.
 */

public class EvaluatePresenter extends RxPresenter<EvaluateContract.View> implements EvaluateContract.Presenter {
    private final DataManager dataManager;
    private static final String EVALUATE_TAG_TYPE = "教评";
    private static final String OPERATOR_TYPE = "教师";

    @Inject
    public EvaluatePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getEvaluateTags( ) {
        Subscription subscription = dataManager.getEvaluateTags(EVALUATE_TAG_TYPE,
                SharedPreferencesUtils.getUserName(), OPERATOR_TYPE,
                SharedPreferencesUtils.getToken(),SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<EvaluateTag>>>rxSchedulerIo())
                .compose(RxUtils.<List<EvaluateTag>>handleHttpResult())
                .subscribe(new Subscriber<List<EvaluateTag>>() {
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
                        view.showTagsError(e);
                    }

                    @Override
                    public void onNext(List<EvaluateTag> evaluateTags) {
                        view.showEvaluateTags(evaluateTags);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void evaluate(String evaluateType, String teacherType, String evaluatedId,
                         String recordId, String evaluateLabel, String evaluateComment) {
        Subscription subscription = dataManager.evaluate(evaluateType, teacherType, evaluatedId,
                recordId, evaluateLabel, evaluateComment, SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
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
