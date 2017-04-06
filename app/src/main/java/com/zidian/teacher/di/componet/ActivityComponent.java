package com.zidian.teacher.di.componet;


import com.zidian.teacher.di.PerActivity;
import com.zidian.teacher.di.module.ActivityModule;
import com.zidian.teacher.ui.main.LoginActivity;
import com.zidian.teacher.ui.main.MainActivity;
import com.zidian.teacher.ui.mine.activity.ChangePasswordActivity;

import dagger.Component;

/**
 * Created by GongCheng on 2017/3/15.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(ChangePasswordActivity changePasswordActivity);
}
