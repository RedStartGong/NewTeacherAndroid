package com.zidian.teacher;

import android.app.Application;
import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.zidian.teacher.di.componet.ApplicationComponent;
import com.zidian.teacher.di.componet.DaggerApplicationComponent;
import com.zidian.teacher.di.module.ApplicationModule;

/**
 * application
 * Created by GongCheng on 2017/3/14.
 */

public class App extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this).build();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        //init Logger and LeakCanary
        if (BuildConfig.DEBUG) {
            Logger.init();
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        } else {
            //初始化腾讯Bugly，用于接收崩溃日志
            CrashReport.initCrashReport(this,BuildConfig.BUGLY_APP_ID,true);
        }
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
