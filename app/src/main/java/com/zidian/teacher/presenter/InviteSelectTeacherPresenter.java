package com.zidian.teacher.presenter;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.College;
import com.zidian.teacher.model.entity.remote.EvaTeacher;
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
 * 查找邀请评价的老师 presenter
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
    public void getColleges() {
        Subscription subscription = dataManager.getColleges(SharedPreferencesUtils.getTeacherId())
                .compose(RxUtils.<List<College>>handleHttpResult())
                .compose(RxUtils.<List<College>>rxSchedulerIo())
                .subscribe(new Subscriber<List<College>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<College> colleges) {
                        view.showColleges(colleges);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getTeachers(@NonNull int collegeId) {
        Subscription subscription = dataManager.getTeachers(SharedPreferencesUtils.getTeacherId(), collegeId)
                .compose(RxUtils.<HttpResult<List<EvaTeacher>>>rxSchedulerIo())
                .compose(RxUtils.<List<EvaTeacher>>handleHttpResult())
                .subscribe(new Subscriber<List<EvaTeacher>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showEmpty();
                    }

                    @Override
                    public void onNext(List<EvaTeacher> evaTeachers) {
                        view.showTeachers(evaTeachers);
                    }
                });
        addSubscribe(subscription);
    }
}
