package com.zidian.teacher.ui.evaluate.fragment;

import android.os.Bundle;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;

/**
 * Created by GongCheng on 2017/4/12.
 */

public class UnconfirmedFragment extends BaseFragment {
    public static UnconfirmedFragment newInstance() {

        Bundle args = new Bundle();

        UnconfirmedFragment fragment = new UnconfirmedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_base_recycler_view;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initViewAndData() {

    }
}
