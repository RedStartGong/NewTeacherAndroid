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
        void showEvaluateTags(List<EvaluateTag> evaluateTags);

        void showTagsError(Throwable e);

        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<EvaluateContract.View> {
        void getEvaluateTags();

        void evaluate(String evaluateType, String teacherType, String evaluatedId, String recordId,
                      String evaluateLabel, String evaluateComment);
    }
}
