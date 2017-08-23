package com.zidian.teacher.presenter.contract;

import android.support.annotation.NonNull;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.College;
import com.zidian.teacher.model.entity.remote.EvaTeacher;
import com.zidian.teacher.model.entity.remote.InviteTeacher;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/14.
 */

public interface InviteSelectTeacherContract {
    interface View extends BaseView {
        void showLoading();

        void showEmpty();

        void showColleges(List<College> colleges);

        void showTeachers(List<EvaTeacher> teachers);

    }

    interface Presenter extends BasePresenter<InviteSelectTeacherContract.View> {
        void getColleges();

        void getTeachers(@NonNull int collegeId);

    }
}
