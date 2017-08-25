package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.SelectClass;

import java.util.List;

/**
 * Created by GongCheng on 2017/5/8.
 */

public interface SelectClassContract {
    interface View extends BaseView {
        void showClasses(List<SelectClass> classes);
    }

    interface Presenter extends BasePresenter<SelectClassContract.View> {
        void getAllClasses();
    }
}
