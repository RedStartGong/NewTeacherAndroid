package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;

/**
 * Created by GongCheng on 2017/5/5.
 */

public interface QuestionnaireAddContract {
    interface View extends BaseView {
        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<QuestionnaireAddContract.View> {
        void addQuestionnaire(String classList, String quesTitle, String quesRemark, String quesItems);
    }
}
