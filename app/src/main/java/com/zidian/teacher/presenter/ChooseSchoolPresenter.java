package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.School;
import com.zidian.teacher.presenter.contract.ChooseSchoolContract;
import com.zidian.teacher.util.RxUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by GongCheng on 2017/8/18.
 */

public class ChooseSchoolPresenter extends RxPresenter<ChooseSchoolContract.View>
        implements ChooseSchoolContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public ChooseSchoolPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getSchools() {
        Subscription subscription = dataManager.getSchools()
                .compose(RxUtils.<HttpResult<List<School>>>rxSchedulerIo())
                .compose(RxUtils.<List<School>>handleHttpResult())
                .subscribe(new Action1<List<School>>() {
                    @Override
                    public void call(List<School> schools) {
                        view.showSchools(schools);
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
