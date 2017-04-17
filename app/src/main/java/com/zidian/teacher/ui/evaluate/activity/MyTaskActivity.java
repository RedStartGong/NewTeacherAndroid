package com.zidian.teacher.ui.evaluate.activity;

import android.support.annotation.StringDef;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.base.BaseFragmentPagerAdapter;
import com.zidian.teacher.ui.evaluate.fragment.MyTasksFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
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
    @StringDef({
            TaskType.UN_CONFIRMED,
            TaskType.UN_EVALUATE,
            TaskType.FINISHED
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TaskType{
        String UN_CONFIRMED = "0";
        String UN_EVALUATE = "1";
        String FINISHED = "3";
    }
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
        fragments.add(MyTasksFragment.newInstance(TaskType.UN_CONFIRMED));
        fragments.add(MyTasksFragment.newInstance(TaskType.UN_EVALUATE));
        fragments.add(MyTasksFragment.newInstance(TaskType.FINISHED));
        titles.add("待确认");
        titles.add("待评价");
        titles.add("已完成");
        pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        vpMyTask.setAdapter(pagerAdapter);
        tlMyTask.setupWithViewPager(vpMyTask);
    }
}
