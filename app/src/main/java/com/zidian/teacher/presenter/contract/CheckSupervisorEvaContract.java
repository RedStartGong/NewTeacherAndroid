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

    }

    interface Presenter extends BasePresenter<CheckSupervisorEvaContract.View> {
        void getEvaTags(String recordId);

    }
}
