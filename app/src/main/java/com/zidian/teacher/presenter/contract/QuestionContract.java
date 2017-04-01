package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.Question;

import java.util.List;


/**
 * Created by GongCheng on 2017/3/31.
 */

public interface QuestionContract {
    interface View extends BaseView {
        void showLoading();

        void showSuccess();

        void showQuestions(List<Question> questions);
    }

    interface Presenter extends BasePresenter<View> {
        void getQuestions(@NonNull String startrow, @NonNull String type);
    }
}
