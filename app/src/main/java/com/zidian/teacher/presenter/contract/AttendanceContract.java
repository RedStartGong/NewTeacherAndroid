package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.Class;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/8.
 */

public interface AttendanceContract {
    interface View extends BaseView {
        void showClasses(List<Class> classes);

        void showLoadingStudents();

        void showLoadStudentsError(Throwable throwable);

        void showStudents(List<AttendanceStudent.DataBean> students );

        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<AttendanceContract.View> {
        void getClasses(String courseId);

        void getAttendanceStudents(String courseWeeklyId, String courseId, String className);

        void setAttendance(String student, String courseId, String courseWeeklyId);
    }
}
