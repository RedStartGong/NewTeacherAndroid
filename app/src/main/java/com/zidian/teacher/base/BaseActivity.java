package com.zidian.teacher.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.zidian.teacher.App;
import com.zidian.teacher.di.componet.ActivityComponent;
import com.zidian.teacher.di.componet.DaggerActivityComponent;
import com.zidian.teacher.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * BaseActivity
 * Created by GongCheng on 2017/3/14.
 */

public abstract class BaseActivity extends SupportActivity {
    private Unbinder unbinder;
    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        unbinder = ButterKnife.bind(this);
        initInject();
        initViewAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(App.get(this).getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return activityComponent;
    }

    protected abstract int getLayout();

    protected abstract void initInject();

    protected abstract void initViewAndData();
}
