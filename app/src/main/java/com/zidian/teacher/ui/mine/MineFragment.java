package com.zidian.teacher.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/3/15.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle(R.string.main_mine);
    }
}
