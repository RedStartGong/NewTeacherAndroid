package com.zidian.teacher.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zidian.teacher.App;
import com.zidian.teacher.R;
import com.zidian.teacher.di.componet.ActivityComponent;
import com.zidian.teacher.di.componet.DaggerActivityComponent;
import com.zidian.teacher.di.module.ActivityModule;
import com.zidian.teacher.ui.main.LoginActivity;
import com.zidian.teacher.ui.main.MainActivity;
import com.zidian.teacher.util.ActManager;
import com.zidian.teacher.util.SharedPreferencesUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

import static dagger.internal.Preconditions.checkNotNull;

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
        //如果判断为已经登录，则直接跳过登录界面
        if (this instanceof LoginActivity) {
            if (SharedPreferencesUtils.getIsLogin()) {
                startActivity(new Intent(this, MainActivity.class));
            }
        }
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

    @SuppressWarnings("deprecation")
    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(App.get(this).getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return activityComponent;
    }

    /**
     * 为toolbar添加返回按钮
     *
     * @param toolbar toolbar
     */
    protected void setToolbarBack(@NonNull Toolbar toolbar) {
        if (this instanceof LoginActivity || this instanceof MainActivity) {
            throw new RuntimeException("Can't set toolbar back icon for this Activity");
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }


    protected abstract int getLayout();

    protected abstract void initInject();

    protected abstract void initViewAndData();
}
