package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.InviteCourseResult;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/13.
 */

public interface InviteContract {
    interface View extends BaseView {
        void showLoading();

        void showInviteCourses(List<InviteCourseResult.CourseBean> courses);

        void showSuccess();
    }

    interface Presenter extends BasePresenter<InviteContract.View> {
        void getInviteCourses();

        void invite(String requestedPerson,  String teacherCollege, String courseId,
                    String courseName, String teachingCalendar, String classroom, String requestExplain);
    }
}
