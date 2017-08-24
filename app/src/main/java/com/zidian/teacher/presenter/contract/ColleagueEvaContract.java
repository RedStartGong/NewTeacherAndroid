package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.ColleagueEva;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/28.
 */

public interface ColleagueEvaContract {
    interface View extends BaseView {
        void showLoading();

        void showColleagueEva(List<ColleagueEva> colleagueEvaList);
    }
    interface Presenter extends BasePresenter<ColleagueEvaContract.View> {
        void getColleagueEva();

        void getSupervisorEva();
    }
}
