package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.Questionnaire;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/1.
 */

public interface QuestionnaireContract {
    interface View extends BaseView {
        void showLoading();

        void showEmpty();

        void showQuestionnaires(@NonNull List<Questionnaire> questionnaires);
    }

    interface Presenter extends BasePresenter<View> {
        void getQuestionnaires(@NonNull String startRow);
    }
}
