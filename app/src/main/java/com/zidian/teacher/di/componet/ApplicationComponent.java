package com.zidian.teacher.di.componet;

import android.app.Application;
import android.content.Context;


import com.zidian.teacher.di.ApplicationContext;
import com.zidian.teacher.di.module.ApplicationModule;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.util.ActManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ApplicationComponent 管理 App 依赖注入，singleton
 * Created by GongCheng on 2017/3/14.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ApplicationContext
    Context context();

    @SuppressWarnings("all")
    Application application();

    DataManager dataManager();

    ActManager actManager();
}
