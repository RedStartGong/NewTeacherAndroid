package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;

/**
 * Created by GongCheng on 2017/4/10.
 */

public interface FeedbackContract {
    interface View extends BaseView {
        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<FeedbackContract.View> {
        void feedback(@NonNull String feedbackContent);
    }
}
