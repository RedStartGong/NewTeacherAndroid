package com.zidian.teacher;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zidian.teacher.di.componet.ApplicationComponent;
import com.zidian.teacher.di.componet.DaggerApplicationComponent;
import com.zidian.teacher.di.module.ApplicationModule;
import com.zidian.teacher.util.SharedPreferencesUtils;

/**
 * Created by GongCheng on 2017/3/14.
 */

public class App extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Logger.init();
        }
        SharedPreferencesUtils.init(this);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
