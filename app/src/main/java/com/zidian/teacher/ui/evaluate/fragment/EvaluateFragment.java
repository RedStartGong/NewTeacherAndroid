package com.zidian.teacher.ui.evaluate.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/3/15.
 */

public class EvaluateFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static EvaluateFragment newInstance() {

        Bundle args = new Bundle();

        EvaluateFragment fragment = new EvaluateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_evaluate;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle(R.string.main_evaluate);
    }
}
