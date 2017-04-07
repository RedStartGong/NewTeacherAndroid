package com.zidian.teacher.di.module;

import android.app.Application;
import android.content.Context;


import com.zidian.teacher.di.ApplicationContext;
import com.zidian.teacher.model.network.ServiceFactory;
import com.zidian.teacher.model.network.TeacherService;
import com.zidian.teacher.util.ActManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by GongCheng on 2017/3/14.
 */
@Module
public class ApplicationModule {
    @SuppressWarnings("deprecation")
    private final Application application;

    @SuppressWarnings("deprecation")
    public ApplicationModule(Application application) {
        this.application = application;
    }

    @SuppressWarnings("deprecation")
    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    TeacherService provideStudentService() {
        return ServiceFactory.makeStudentService();
    }

    @Provides
    @Singleton
    ActManager provideActManager() {
        return new ActManager();
    }
}
