package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.QuesSurveyDetail;

/**
 * Created by GongCheng on 2017/5/3.
 */

public interface QuesSurveyDetailContract {
    interface View extends BaseView {
        void showQuesDetail(QuesSurveyDetail quesSurveyDetail);

        void showSubmitLoading();

        void showSubmitSuccess();

    }

    interface Presenter extends BasePresenter<QuesSurveyDetailContract.View> {
        void getQuesDetail(String questionnaireId);

        void quesSubmit(String quesSubmit);
    }
}
