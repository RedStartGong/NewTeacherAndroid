package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.model.entity.remote.CourseTime;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/7.
 */

public interface ScheduleContract {
    interface View extends BaseView {
        void showSchedule(List<Course> courses);

        void showLoading();

        void showCourseTime(CourseTime courseTime);
    }

    interface Presenter extends BasePresenter<View> {
        void getSchedule(@NonNull int week);

        void getCourseTime(@NonNull int week);
    }
}
