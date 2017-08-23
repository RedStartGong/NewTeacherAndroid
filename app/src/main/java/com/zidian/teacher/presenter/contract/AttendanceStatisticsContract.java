package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.StudentClass;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/9.
 */

public interface AttendanceStatisticsContract {
    interface View extends BaseView {
        void showClasses(List<StudentClass> classes);

        void showLoading();

        void showSuccess();

        void showStatistics(List<AttendanceStatistics> attendanceStatistics);

    }

    interface Presenter extends BasePresenter<AttendanceStatisticsContract.View> {
        void getClasses(int courseId);

        void getAttendanceStatistics(int courseId, String className);
    }
}
