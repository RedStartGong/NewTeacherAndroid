package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.EvaTwoIndex;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.presenter.contract.StudentEvaTwoIndexContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 学生评价二级指标 presenter
 * Created by GongCheng on 2017/4/28.
 */

public class StudentEvaTwoIndexPresenter extends RxPresenter<StudentEvaTwoIndexContract.View>
        implements StudentEvaTwoIndexContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public StudentEvaTwoIndexPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getStudentEvaTwoIndex(String indexName) {
        Subscription subscription = dataManager.studentEvaTwoIndex(indexName,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<EvaTwoIndex>>>rxSchedulerIo())
                .compose(RxUtils.<List<EvaTwoIndex>>handleHttpResult())
                .subscribe(new Action1<List<EvaTwoIndex>>() {
                    @Override
                    public void call(List<EvaTwoIndex> evaTwoIndices) {
                        view.showEvaTwoIndex(evaTwoIndices);
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
