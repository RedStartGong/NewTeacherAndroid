package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.InviteTeacher;
import com.zidian.teacher.presenter.contract.InviteSelectTeacherContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by GongCheng on 2017/4/14.
 */

public class InviteSelectTeacherPresenter extends RxPresenter<InviteSelectTeacherContract.View>
        implements InviteSelectTeacherContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public InviteSelectTeacherPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getInviteTeachers(String condition) {
        Subscription subscription = dataManager.getInviteTeachers(condition,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<InviteTeacher>>>rxSchedulerIo())
                .compose(RxUtils.<List<InviteTeacher>>handleHttpResult())
                .subscribe(new Subscriber<List<InviteTeacher>>() {
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
                    public void onNext(List<InviteTeacher> teachers) {
                        if (teachers.isEmpty()) {
                            view.showEmpty();
                        } else {
                            view.showInviteTeachers(teachers);
                        }
                    }
                });
        addSubscribe(subscription);
    }
}
