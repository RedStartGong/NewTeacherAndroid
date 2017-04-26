package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;

/**
 * Created by GongCheng on 2017/4/25.
 */

public interface CheckSupervisorEvaContract {
    interface View extends BaseView {
        void showLoading();

        void showEvaTags(CheckSupervisorEva checkSupervisorEva);

        void showFeedbackSucceed();

        void showFeedbackError(Throwable throwable);
    }

    interface Presenter extends BasePresenter<CheckSupervisorEvaContract.View> {
        void getEvaTags(String recordId);

        void confirm(String recordId);
    }
}
