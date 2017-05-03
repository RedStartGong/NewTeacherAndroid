package com.zidian.teacher.ui.questionnaire.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.base.BaseFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/1.
 */

public class QuestionnaireFragment extends BaseFragment {
    @BindView(R.id.fab_add_questionnaire)
    FloatingActionButton fabAddQuestionnaire;
    @BindView(R.id.tl_questionnaire)
    TabLayout tlQuestionnaire;
    @BindView(R.id.vp_questionnaire)
    ViewPager vpQuestionnaire;

    public static QuestionnaireFragment newInstance() {

        Bundle args = new Bundle();

        QuestionnaireFragment fragment = new QuestionnaireFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_questionnaire;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initViewAndData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(QuestionnaireSurveyFragment.newInstance());
        fragments.add(MyQuestionnaireFragment.newInstance());
        List<String> titles = new ArrayList<String>() {
            {
                add("问卷调查");
                add("我的问卷");
            }
        };
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(
                getChildFragmentManager(), fragments, titles);
        vpQuestionnaire.setAdapter(pagerAdapter);
        tlQuestionnaire.setupWithViewPager(vpQuestionnaire);
    }
}
