package com.zidian.teacher.ui.evaluate.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class MyEvaluateActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tl_my_evaluate)
    TabLayout tlMyEvaluate;
    @BindView(R.id.vp_my_evaluate)
    ViewPager vpMyEvaluate;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_evaluate;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle("我的评价");
        setToolbarBack(toolbar);

    }
}
