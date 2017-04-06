package com.zidian.teacher.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zidian.teacher.App;
import com.zidian.teacher.di.componet.DaggerFragmentComponent;
import com.zidian.teacher.di.componet.FragmentComponent;
import com.zidian.teacher.di.module.FragmentModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * BaseFragment
 * Created by GongCheng on 2017/3/14.
 */

public abstract class BaseFragment extends SupportFragment {
    protected Context context;
    protected Activity activity;
    protected FragmentComponent fragmentComponent;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initInject();
        initViewAndData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected FragmentComponent getFragmentComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = DaggerFragmentComponent.builder()
                    .applicationComponent(App.get(getContext()).getApplicationComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return fragmentComponent;
    }

    protected abstract int getLayout();

    protected abstract void initInject();

    protected abstract void initViewAndData();
}
