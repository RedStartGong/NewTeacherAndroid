package com.zidian.teacher.presenter.contract;


import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.School;

import java.util.List;

/**
 * Created by GongCheng on 2017/8/18.
 */

public interface ChooseSchoolContract {
    interface View extends BaseView {
        void showSchools(List<School> schools);
    }

    interface Presenter extends BasePresenter<View> {
        void getSchools();
    }
}
