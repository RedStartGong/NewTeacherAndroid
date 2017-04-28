package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.StudentEva;

/**
 * Created by GongCheng on 2017/4/28.
 */

public interface StudentEvaContract {
    interface View extends BaseView {
        void showLoading();

        void showStudentEva(StudentEva studentEva);
    }

    interface Presenter extends BasePresenter<StudentEvaContract.View> {
        void getStudentEva();
    }
}
