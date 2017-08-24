package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.EvaluateTag;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/20.
 */

public interface EvaluateContract {
    interface View extends BaseView {
        void showEvaluateTag(EvaluateTag evaluateTag);

        void showTagsError(Throwable e);

        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<EvaluateContract.View> {
        void getEvaluateTags(int requestEvalMessageId);

        void evaluate(int requestEvalMessageId, int toTeacherId,
                      int evaluateType, String evaluateContent,
                      String customEva);
    }
}
