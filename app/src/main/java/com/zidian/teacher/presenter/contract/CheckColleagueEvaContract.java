package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.EvaluateTag;

/**
 * Created by GongCheng on 2017/4/24.
 */

public interface CheckColleagueEvaContract {
    interface View extends BaseView {
        void showLoading();

        void showEvaTag(EvaluateTag evaluateTag);

    }

    interface Presenter extends BasePresenter<CheckColleagueEvaContract.View> {
        void getEvaluateTags(int requestEvalMessageId);
    }
}
