package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.College;
import com.zidian.teacher.model.entity.remote.CoursePlan;
import com.zidian.teacher.model.entity.remote.EvaCourse;
import com.zidian.teacher.model.entity.remote.EvaTeacher;
import com.zidian.teacher.model.entity.remote.EvaluateCourse;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/19.
 */

public interface ApplyToEvaContract {
    interface View extends BaseView {

        void showColleges(List<College> colleges);

        void showEvaTeachers(List<EvaTeacher> evaTeachers);

        void showEvaCourses(List<EvaCourse> evaCourses);

        void showEvaCoursePlans(List<CoursePlan> coursePlans);

        void showLoading();

        void showSuccess();
    }

    interface Presenter extends BasePresenter<ApplyToEvaContract.View> {
        void getColleges();

        void getEvaTeachers(@NonNull int collegeId);

        void getEvaCourses(@NonNull int evaTeacherId);

        void getEvaCoursePlans(@NonNull int evaTeacherId, @NonNull int courseId);

        void apply(@NonNull String toTeacherId, @NonNull String requestMessage,
                   @NonNull int evaluateType, @NonNull int coursePlanId);
    }
}
