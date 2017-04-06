package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.PersonInfo;

/**
 * Created by GongCheng on 2017/4/6.
 */

public interface PersonInfoContract {
    interface View extends BaseView {
        void showInfo(PersonInfo personInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonInfo();
    }
}
