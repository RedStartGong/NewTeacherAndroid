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
        void showLoading(String loadingMsg);

        void showSuccess(String successMsg);
    }

    interface Presenter extends BasePresenter<ChangeInfoContract.View> {
        void setPortrait(RequestBody teacherId, RequestBody token, RequestBody schoolId, MultipartBody.Part image);

        void setPersonInfo(String motto, String phoneNumber, String teacherSex, String birthday, String nickName);
    }
}
