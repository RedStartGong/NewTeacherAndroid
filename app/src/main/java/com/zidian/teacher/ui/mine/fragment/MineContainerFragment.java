package com.zidian.teacher.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zidian.teacher.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 容器Fragment
 * Created by GongCheng on 2017/4/7.
 */

public class MineContainerFragment extends SupportFragment {
    public static MineContainerFragment newInstance() {

        Bundle args = new Bundle();

        MineContainerFragment fragment = new MineContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        loadRootFragment(R.id.fl_container, MineFragment.newInstance());
        return view;
    }
}
