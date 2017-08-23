package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.EvaluateCourse;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/19.
 */

public interface ApplyToEvaContract {
    interface View extends BaseView {
        void showLoading();

        void showEvaluateCourses(List<EvaluateCourse> courses);

        void showSuccess();
    }

    interface Presenter extends BasePresenter<ApplyToEvaContract.View> {
        void getEvaluateCourses();

        void apply(String requestedPerson, String teacherCollege, int courseId, String courseName,
                   String teachingCalendar, String classroom, String requestExplain);
    }
}
