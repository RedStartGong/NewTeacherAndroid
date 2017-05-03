package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.QuesSurveyList;

/**
 * Created by GongCheng on 2017/5/3.
 */

public interface QuestionnaireSurveyContract {
    interface View extends BaseView {
        void showEmpty();

        void showQuestionnaireSurveyList(QuesSurveyList quesSurveyLists);
    }

    interface Presenter extends BasePresenter<QuestionnaireSurveyContract.View> {
        void getQuestionnaireSurveyList(String startRow);
    }
}
