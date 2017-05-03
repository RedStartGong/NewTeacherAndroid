package com.zidian.teacher.ui.questionnaire.fragment;

import android.os.Bundle;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.xrecyclerview.XRecyclerView;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/5/2.
 */

public class MyQuestionnaireFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;

    public static MyQuestionnaireFragment newInstance() {

        Bundle args = new Bundle();

        MyQuestionnaireFragment fragment = new MyQuestionnaireFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_base_recycler_view;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {

    }
}
