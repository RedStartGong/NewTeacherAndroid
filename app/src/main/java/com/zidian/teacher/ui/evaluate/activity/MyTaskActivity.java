package com.zidian.teacher.ui.evaluate.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.base.BaseFragmentPagerAdapter;
import com.zidian.teacher.ui.evaluate.fragment.UnconfirmedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/12.
 */

public class MyTaskActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tl_my_task)
    TabLayout tlMyTask;
    @BindView(R.id.vp_my_task)
    ViewPager vpMyTask;

    private BaseFragmentPagerAdapter pagerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_task;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle("我的任务");
        setToolbarBack(toolbar);
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        fragments.add(UnconfirmedFragment.newInstance());
        fragments.add(UnconfirmedFragment.newInstance());
        fragments.add(UnconfirmedFragment.newInstance());
        titles.add("待确认");
        titles.add("待评价");
        titles.add("已完成");
        pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        vpMyTask.setAdapter(pagerAdapter);
        tlMyTask.setupWithViewPager(vpMyTask);
    }
}
