package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
import com.zidian.teacher.model.entity.remote.EvaluateTag;

/**
 * Created by GongCheng on 2017/4/25.
 */

public interface CheckSupervisorEvaContract {
    interface View extends BaseView {
        void showLoading();

        void showEvaTag(EvaluateTag evaluateTag);

        void showFeedbackSucceed();

        void showFeedbackError(Throwable throwable);
    }

    interface Presenter extends BasePresenter<CheckSupervisorEvaContract.View> {
        void getEvaluateTag(int requestEvalMessageId);

        void confirm(String recordId);
    }
}
