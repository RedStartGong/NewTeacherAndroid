package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.Class;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/8.
 */

public interface AttendanceContract {
    interface View extends BaseView {
        void showClasses(List<Class> classes);

        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<AttendanceContract.View> {
        void getClasses(String courseId);

        void setAttendance(String student, String courseId, String courseWeeklyId);
    }
}
