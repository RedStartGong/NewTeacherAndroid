package com.zidian.teacher.ui.evaluate.fragment;

import android.os.Bundle;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class CustomEvaFragment extends BaseFragment {
    public static CustomEvaFragment newInstance() {

        Bundle args = new Bundle();

        CustomEvaFragment fragment = new CustomEvaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_base_eva;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {

    }
}
