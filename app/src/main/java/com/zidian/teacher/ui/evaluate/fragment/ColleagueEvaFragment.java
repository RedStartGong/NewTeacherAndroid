package com.zidian.teacher.ui.evaluate.fragment;

import android.os.Bundle;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class ColleagueEvaFragment extends BaseFragment {
    private String evaluateType;

    public static ColleagueEvaFragment newInstance(String evaluateType) {

        Bundle args = new Bundle();

        ColleagueEvaFragment fragment = new ColleagueEvaFragment();
        fragment.evaluateType = evaluateType;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_colleague_eva;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {

    }
}
