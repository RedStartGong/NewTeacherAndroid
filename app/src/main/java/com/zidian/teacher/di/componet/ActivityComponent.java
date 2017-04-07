package com.zidian.teacher.di.componet;


import com.zidian.teacher.di.PerActivity;
import com.zidian.teacher.di.module.ActivityModule;
import com.zidian.teacher.ui.course.activity.AttendanceActivity;
import com.zidian.teacher.ui.course.activity.AttendanceStatisticsActivity;
import com.zidian.teacher.ui.course.activity.CourseInfoActivity;
import com.zidian.teacher.ui.main.LoginActivity;
import com.zidian.teacher.ui.main.MainActivity;
import com.zidian.teacher.ui.mine.activity.AboutActivity;
import com.zidian.teacher.ui.mine.activity.ChangeInfoActivity;

import dagger.Component;

/**
 * Created by GongCheng on 2017/3/15.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(AboutActivity aboutActivity);

    void inject(ChangeInfoActivity changeInfoActivity);

    void inject(CourseInfoActivity courseInfoActivity);

    void inject(AttendanceActivity attendanceActivity);

    void inject(AttendanceStatisticsActivity attendanceStatisticsActivity);
}
