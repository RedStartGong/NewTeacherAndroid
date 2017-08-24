package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.CustomEva;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/28.
 */

public interface CustomEvaContract {
    interface View extends BaseView {
        void showEmpty();

        void showCustomEva(List<CustomEva> customEvas);
    }

    interface Presenter extends BasePresenter<CustomEvaContract.View> {
        void getCustomEva(int startRow);
    }
}
