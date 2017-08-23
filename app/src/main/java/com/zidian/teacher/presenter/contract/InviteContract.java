package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.CoursePlan;
import com.zidian.teacher.model.entity.remote.EvaCourse;
import com.zidian.teacher.model.entity.remote.InviteCourseResult;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/13.
 */

public interface InviteContract {
    interface View extends BaseView {
        void showLoading();

        void showEvaCourses(List<EvaCourse> evaCourses);

        void showCoursePlans(List<CoursePlan> coursePlans);

        void showSuccess();
    }

    interface Presenter extends BasePresenter<InviteContract.View> {
        void getCourses();

        void getCoursePlans(int courseId);


        void invite( String toTeacherId, String requestMessage, int evaluateType, int coursePlanId);
    }
}
