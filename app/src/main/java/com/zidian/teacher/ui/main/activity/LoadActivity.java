package com.zidian.teacher.ui.main.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.zidian.teacher.R;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.lang.ref.WeakReference;

/**
 * 欢迎界面
 * Created by GongCheng on 2017/5/9.
 */

public class LoadActivity extends AppCompatActivity {
    private StaticHandler handler = new StaticHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_load);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    /**
     * 静态内部类handler
     */
    static class StaticHandler extends Handler {
        WeakReference<LoadActivity> activityWeakReference;

        StaticHandler(LoadActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LoadActivity activity = activityWeakReference.get();
            super.handleMessage(msg);
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        if (SharedPreferencesUtils.getIsLogin()) {
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        break;
                }
            }
        }
    }

}
