package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;

/**
 * Created by GongCheng on 2017/4/26.
 */

public interface SupervisorFeedbackContract {
    interface View extends BaseView {
        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<SupervisorFeedbackContract.View> {
        void feedback(int requestEvalMessageId, String dissentDesc);
    }
}
