package com.zidian.teacher.presenter;

import com.zidian.teacher.base.RxPresenter;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.QuesSurveyList;
import com.zidian.teacher.presenter.contract.QuestionnaireSurveyContract;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class QuestionnaireSurveyPresenter extends RxPresenter<QuestionnaireSurveyContract.View>
        implements QuestionnaireSurveyContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public QuestionnaireSurveyPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getQuestionnaireSurveyList(String startRow) {
        Subscription subscription = dataManager.quesSurveyList(startRow, "10",
                SharedPreferencesUtils.getUserName(), SharedPreferencesUtils.getToken(),
                SharedPreferencesUtils.getSchoolId())
                .compose(RxUtils.<HttpResult<QuesSurveyList>>rxSchedulerIo())
                .compose(RxUtils.<QuesSurveyList>handleHttpResult())
                .subscribe(new Subscriber<QuesSurveyList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(QuesSurveyList quesSurveyList) {
                        if (quesSurveyList.getQuestionnaireList().size() == 0) {
                            view.showEmpty();
                        } else {
                            view.showQuestionnaireSurveyList(quesSurveyList);
                        }
                    }
                });
        addSubscribe(subscription);
    }
}
