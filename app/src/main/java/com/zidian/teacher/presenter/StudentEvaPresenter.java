package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.StudentEva;
import com.zidian.teacher.presenter.contract.StudentEvaContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by GongCheng on 2017/4/28.
 */

public class StudentEvaPresenter extends RxPresenter<StudentEvaContract.View>
        implements StudentEvaContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public StudentEvaPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getStudentEva() {
        Subscription subscription = dataManager.studentEva(SharedPreferencesUtils.getUserName(),
                SharedPreferencesUtils.getToken(), SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<StudentEva>>rxSchedulerIo())
                .compose(RxUtils.<StudentEva>handleHttpResult())
                .subscribe(new Subscriber<StudentEva>() {
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
                    public void onNext(StudentEva studentEva) {
                        view.showStudentEva(studentEva);
                    }
                });
        addSubscribe(subscription);
    }
}
