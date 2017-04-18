package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.EvaluateCourse;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/18.
 */

public interface SupervisorChooseContract {
    interface View extends BaseView {
        void showLoading();

        void showEvaluateCourses(List<EvaluateCourse> courses);

    }

    interface Presenter extends BasePresenter<SupervisorChooseContract.View> {
        void getEvaluateCourses();
    }
}
