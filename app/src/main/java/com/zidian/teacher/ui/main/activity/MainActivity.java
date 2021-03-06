package com.zidian.teacher.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.ui.course.fragment.CourseFragment;
import com.zidian.teacher.ui.evaluate.fragment.EvaluateFragment;
import com.zidian.teacher.ui.mine.fragment.MineContainerFragment;
import com.zidian.teacher.ui.mine.fragment.MineFragment;
import com.zidian.teacher.ui.questionnaire.fragment.QuestionnaireFragment;
import com.zidian.teacher.ui.widget.BottomBar;
import com.zidian.teacher.ui.widget.BottomBarTab;
import com.zidian.teacher.util.ActManager;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    @Inject
    ActManager actManager;

    private static final int COURSE = 0;
    private static final int EVALUATE = 1;
    private static final int QUESTIONNAIRE = 2;
    private static final int MINE = 3;

    private SupportFragment[] supportFragments = new SupportFragment[4];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actManager.addActivity(this);
        if (savedInstanceState == null) {
            supportFragments[COURSE] = CourseFragment.newInstance();
            supportFragments[EVALUATE] = EvaluateFragment.newInstance();
            supportFragments[QUESTIONNAIRE] = QuestionnaireFragment.newInstance();
            supportFragments[MINE] = MineContainerFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, COURSE,
                    supportFragments[COURSE],
                    supportFragments[EVALUATE],
                    supportFragments[QUESTIONNAIRE],
                    supportFragments[MINE]);
        } else {
            supportFragments[COURSE] = findFragment(CourseFragment.class);
            supportFragments[EVALUATE] = findFragment(EvaluateFragment.class);
            supportFragments[QUESTIONNAIRE] = findFragment(QuestionnaireFragment.class);
            supportFragments[MINE] = findFragment(MineContainerFragment.class);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    /**
     * 设置 Fragment 启动动画
     *
     * @return animator
     */
    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void initViewAndData() {
        bottomBar.addItem(new BottomBarTab(this, R.drawable.ic_course, getString(R.string.main_course)))
                .addItem(new BottomBarTab(this, R.drawable.ic_evaluate, getString(R.string.main_evaluate)))
                .addItem(new BottomBarTab(this, R.drawable.ic_questionnaire, getString(R.string.main_questionnaire)))
                .addItem(new BottomBarTab(this, R.drawable.ic_me, getString(R.string.main_mine)));
        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(supportFragments[position], supportFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                SupportFragment currentFragment = supportFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
                if (count > 1) {
                    if (currentFragment instanceof MineContainerFragment) {
                        currentFragment.popToChild(MineFragment.class, false );
                    }
                }
            }
        });
    }
}
