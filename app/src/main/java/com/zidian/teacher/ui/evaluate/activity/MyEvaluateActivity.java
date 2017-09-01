package com.zidian.teacher.ui.evaluate.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.base.BaseFragmentPagerAdapter;
import com.zidian.teacher.ui.evaluate.fragment.ColleagueEvaFragment;
import com.zidian.teacher.ui.evaluate.fragment.CustomEvaFragment;
import com.zidian.teacher.ui.evaluate.fragment.StudentEvaFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的评价container
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
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        titles.add("学生评价");
        titles.add("同行评价");
        titles.add("督导评价");
        titles.add("自定义评价");
        fragments.add(StudentEvaFragment.newInstance());
        fragments.add(ColleagueEvaFragment.newInstance(2));
        fragments.add(ColleagueEvaFragment.newInstance(3));
        fragments.add(CustomEvaFragment.newInstance());
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(
                getSupportFragmentManager(), fragments, titles);
        vpMyEvaluate.setAdapter(pagerAdapter);
        tlMyEvaluate.setupWithViewPager(vpMyEvaluate);
    }
}
