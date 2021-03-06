package com.zidian.teacher.di.componet;


import com.zidian.teacher.di.PerFragment;
import com.zidian.teacher.di.module.FragmentModule;
import com.zidian.teacher.ui.course.fragment.CourseFragment;
import com.zidian.teacher.ui.evaluate.fragment.ColleagueEvaFragment;
import com.zidian.teacher.ui.evaluate.fragment.CustomEvaFragment;
import com.zidian.teacher.ui.evaluate.fragment.EvaluateFragment;
import com.zidian.teacher.ui.evaluate.fragment.MyTasksFragment;
import com.zidian.teacher.ui.evaluate.fragment.StudentEvaFragment;
import com.zidian.teacher.ui.mine.fragment.ChangePasswordFragment;
import com.zidian.teacher.ui.mine.fragment.MineFragment;
import com.zidian.teacher.ui.questionnaire.fragment.MyQuestionnaireFragment;
import com.zidian.teacher.ui.questionnaire.fragment.QuestionnaireSurveyFragment;
import com.zidian.teacher.ui.questionnaire.fragment.QuestionnaireFragment;

import dagger.Component;

/**
 * FragmentComponent 管理 Fragment 依赖注入
 * Created by GongCheng on 2017/3/15.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(CourseFragment courseFragment);

    void inject(MineFragment mineFragment);

    void inject(EvaluateFragment evaluateFragment);

    void inject(QuestionnaireFragment questionnaireFragment);

    void inject(ChangePasswordFragment changePasswordFragment);

    void inject(MyTasksFragment myTasksFragment);

    void inject(StudentEvaFragment studentEvaFragment);

    void inject(ColleagueEvaFragment colleagueEvaFragment);

    void inject(CustomEvaFragment customEvaFragment);

    void inject(MyQuestionnaireFragment myQuestionnaireFragment);

    void inject(QuestionnaireSurveyFragment questionnaireSurveyFragment);
}
