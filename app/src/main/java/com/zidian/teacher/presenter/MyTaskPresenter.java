package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.MyTask;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.network.ApiException;
import com.zidian.teacher.presenter.contract.MyTaskContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 我的任务 presenter
 * Created by GongCheng on 2017/4/12.
 */

public class MyTaskPresenter extends RxPresenter<MyTaskContract.View> implements MyTaskContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public MyTaskPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getTasks(String requestState) {
        Subscription subscription = dataManager.getMyTasks(requestState, SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<MyTask>>>rxSchedulerIo())
                .compose(RxUtils.<List<MyTask>>handleHttpResult())
                .subscribe(new Action1<List<MyTask>>() {
                    @Override
                    public void call(List<MyTask> tasks) {
                        if (tasks.isEmpty()) {
                            view.showEmpty();
                        } else {
                            view.showTasks(tasks);
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

    @Override
    public void changeEvaState(String recordId, String requestState) {
        Subscription subscription = dataManager.changeEvaState(recordId, requestState,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
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
                .subscribe(new Action1<NoDataResult>() {
                    @Override
                    public void call(NoDataResult noDataResult) {
                        view.showChangeStateSucceed();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showChangeStateError(throwable);
                    }
                });
        addSubscribe(subscription);
    }
}
