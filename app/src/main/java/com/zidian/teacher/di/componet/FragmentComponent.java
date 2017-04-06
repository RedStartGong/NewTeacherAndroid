package com.zidian.teacher.di.componet;


import com.zidian.teacher.di.PerFragment;
import com.zidian.teacher.di.module.FragmentModule;
import com.zidian.teacher.ui.course.fragment.CourseFragment;
import com.zidian.teacher.ui.evaluate.fragment.EvaluateFragment;
import com.zidian.teacher.ui.mine.fragment.MineFragment;
import com.zidian.teacher.ui.questionnaire.QuestionnaireFragment;

import dagger.Component;

/**
 * Created by GongCheng on 2017/3/15.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(CourseFragment courseFragment);

    void inject(MineFragment mineFragment);

    void inject(EvaluateFragment evaluateFragment);

    void inject(QuestionnaireFragment questionnaireFragment);
}
