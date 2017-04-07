package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.Course;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/7.
 */

public interface CourseContract {
    interface View extends BaseView {
        void showCourse(List<Course> courses);
    }

    interface Presenter extends BasePresenter<View> {
        void getCourse();
    }
}
