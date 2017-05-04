package com.zidian.teacher.di.componet;


import com.zidian.teacher.di.PerActivity;
import com.zidian.teacher.di.module.ActivityModule;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
import com.zidian.teacher.ui.course.activity.AttendanceActivity;
import com.zidian.teacher.ui.course.activity.AttendanceStatisticsActivity;
import com.zidian.teacher.ui.course.activity.CourseInfoActivity;
import com.zidian.teacher.ui.evaluate.activity.ApplyToEvaActivity;
import com.zidian.teacher.ui.evaluate.activity.CheckColleagueEvaActivity;
import com.zidian.teacher.ui.evaluate.activity.CheckSupervisorEvaActivity;
import com.zidian.teacher.ui.evaluate.activity.ColleagueEvaActivity;
import com.zidian.teacher.ui.evaluate.activity.ColleagueEvaTwoIndexActivity;
import com.zidian.teacher.ui.evaluate.activity.EvaluateActivity;
import com.zidian.teacher.ui.evaluate.activity.InviteActivity;
import com.zidian.teacher.ui.evaluate.activity.InviteSelectTeacherActivity;
import com.zidian.teacher.ui.evaluate.activity.MyTaskActivity;
import com.zidian.teacher.ui.evaluate.activity.StudentEvaTwoIndexActivity;
import com.zidian.teacher.ui.evaluate.activity.SupervisorEvaActivity;
import com.zidian.teacher.ui.evaluate.activity.SupervisorFeedbackActivity;
import com.zidian.teacher.ui.main.LoginActivity;
import com.zidian.teacher.ui.main.MainActivity;
import com.zidian.teacher.ui.mine.activity.AboutActivity;
import com.zidian.teacher.ui.mine.activity.ChangeInfoActivity;
import com.zidian.teacher.ui.mine.activity.FeedbackActivity;
import com.zidian.teacher.ui.questionnaire.activity.AddQuestionnaireActivity;
import com.zidian.teacher.ui.questionnaire.activity.MyQuesDetailActivity;

import dagger.Component;

/**
 * Activity Component 管理 Activity 依赖注入
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

    void inject(FeedbackActivity feedBackActivity);

    void inject(MyTaskActivity myTaskActivity);

    void inject(ColleagueEvaActivity colleagueEvaActivity);

    void inject(InviteActivity inviteActivity);

    void inject(ApplyToEvaActivity applyForEvaActivity);

    void inject(InviteSelectTeacherActivity inviteSelectTeacherActivity);

    void inject(SupervisorEvaActivity supervisorEvaActivity);

    void inject(EvaluateActivity evaluateActivity);

    void inject(CheckColleagueEvaActivity checkColleagueEvaActivity);

    void inject(CheckSupervisorEvaActivity checkSupervisorEvaActivity);

    void inject(SupervisorFeedbackActivity supervisorFeedbackActivity);

    void inject(StudentEvaTwoIndexActivity studentEvaTwoIndexActivity);

    void inject(ColleagueEvaTwoIndexActivity colleagueEvaTwoIndexActivity);

    void inject(MyQuesDetailActivity myQuesDetailActivity);

    void inject(AddQuestionnaireActivity addQuestionnaireActivity);
}
