package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.CheckColleagueEva;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/24.
 */

public interface CheckColleagueEvaContract {
    interface View extends BaseView {
        void showLoading();

        void showEvaTags(CheckColleagueEva checkColleagueEva);

    }

    interface Presenter extends BasePresenter<CheckColleagueEvaContract.View> {
        void checkColleagueEva(String recordId);
    }
}
