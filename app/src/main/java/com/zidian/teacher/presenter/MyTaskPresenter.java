package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.MyTask;
import com.zidian.teacher.presenter.contract.MyTaskContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
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
    }
}
