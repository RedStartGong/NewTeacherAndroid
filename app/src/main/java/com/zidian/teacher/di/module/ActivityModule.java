package com.zidian.teacher.di.module;

import android.app.Activity;
import android.content.Context;


import com.zidian.teacher.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by GongCheng on 2017/3/15.
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    public Context provideContext() {
        return activity;
    }
}
