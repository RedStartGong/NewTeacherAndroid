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
import com.zidian.teacher.ui.main.activity.LoginActivity;
import com.zidian.teacher.ui.main.activity.MainActivity;
import com.zidian.teacher.util.SharedPreferencesUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * BaseActivity
 * Created by GongCheng on 2017/3/14.
 */

public abstract class BaseActivity extends SupportActivity {
    private Unbinder unbinder;
    protected ActivityComponent activityComponent;
    private CompositeSubscription subscriptions;

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
        unSubscribe();
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

    protected void addSubscribe(Subscription subscription) {
        if (subscriptions == null) {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
    }

    private void unSubscribe() {
        if (subscriptions != null) {
            subscriptions.unsubscribe();
        }
    }


    protected abstract int getLayout();

    protected abstract void initInject();

    protected abstract void initViewAndData();
}
