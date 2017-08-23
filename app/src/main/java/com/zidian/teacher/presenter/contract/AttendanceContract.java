package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.StudentClass;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/8.
 */

public interface AttendanceContract {
    interface View extends BaseView {
        void showClasses(List<StudentClass> classes);

        void showLoadingStudents();

        void showLoadStudentsError(Throwable throwable);

        void showStudents(List<AttendanceStudent> students);

        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<AttendanceContract.View> {
        void getClasses(@NonNull int courseId);

        void getAttendanceStudents(@NonNull int courseId, @NonNull int coursePlanId, @NonNull String className);

        void setAttendance(@NonNull String student, @NonNull int courseId, @NonNull int coursePlanId);
    }
}
