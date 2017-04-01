package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.School;

import java.util.List;


/**
 * Created by GongCheng on 2017/3/20.
 */

public interface LoginContract {
    interface View extends BaseView {
        void showLoading();

        void showSchool(List<School> schools);

        void showSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getSchools();

        void login(@NonNull String username, @NonNull String password, @NonNull String schoolId);
    }
}
