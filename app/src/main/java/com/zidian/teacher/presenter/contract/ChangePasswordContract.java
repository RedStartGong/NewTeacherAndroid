package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;

/**
 * Created by GongCheng on 2017/4/6.
 */

public interface ChangePasswordContract {
    interface View extends BaseView {
        void showSuccess();

        void showLoading();
    }

    interface Presenter extends BasePresenter<ChangePasswordContract.View> {
        void changePassword(@NonNull String password, @NonNull String password1, @NonNull String password2);
    }
}
