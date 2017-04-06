package com.zidian.teacher.ui.questionnaire;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/1.
 */

public class QuestionnaireFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        toolbar.setTitle(R.string.main_questionnaire);
    }
}
