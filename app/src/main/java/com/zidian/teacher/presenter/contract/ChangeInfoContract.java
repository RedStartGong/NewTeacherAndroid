package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by GongCheng on 2017/4/11.
 */

public interface ChangeInfoContract {
    interface View extends BaseView {
        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<ChangeInfoContract.View> {
        void changeUserInfo(RequestBody teacherId, RequestBody aliasName, RequestBody phone,
                             RequestBody signName, RequestBody birthday, RequestBody sex,
                             MultipartBody.Part iconUrl);

        void changeUserInfoNoImg(RequestBody teacherId, RequestBody aliasName, RequestBody phone,
                                 RequestBody signName, RequestBody birthday, RequestBody sex);
    }
}
