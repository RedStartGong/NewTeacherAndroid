package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.MyQuesDetail;
import com.zidian.teacher.presenter.contract.MyQuesDetailContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by GongCheng on 2017/5/4.
 */

public class MyQuesDetailPresenter extends RxPresenter<MyQuesDetailContract.View>
        implements MyQuesDetailContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public MyQuesDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getMyQuesDetail(String questionnaireId) {
        Subscription subscription = dataManager.myQuesDetail(questionnaireId,
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<List<MyQuesDetail>>>rxSchedulerIo())
                .compose(RxUtils.<List<MyQuesDetail>>handleHttpResult())
                .map(new Func1<List<MyQuesDetail>, List<MyQuesDetail>>() {
                    @Override
                    public List<MyQuesDetail> call(List<MyQuesDetail> myQuesDetails) {
                        //对问卷进行排序
                        Collections.sort(myQuesDetails, new Comparator<MyQuesDetail>() {
                            @Override
                            public int compare(MyQuesDetail o1, MyQuesDetail o2) {
                                return o1.getQuestionNum() - o2.getQuestionNum();
                            }
                        });
                        return myQuesDetails;
                    }
                })
                .subscribe(new Action1<List<MyQuesDetail>>() {
                    @Override
                    public void call(List<MyQuesDetail> myQuesDetails) {
                        view.showMyQuesDetail(myQuesDetails);
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
