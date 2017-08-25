package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.MyQuestionnaire;

import java.util.List;

/**
 * Created by GongCheng on 2017/5/3.
 */

public interface MyQuestionnaireListContract  {
    interface View extends BaseView {
        void showEmpty();

        void showMyQues(List<MyQuestionnaire> myQuestionnaires);
    }

    interface Presenter extends BasePresenter<MyQuestionnaireListContract.View> {
        void getMyQues();
    }
}
