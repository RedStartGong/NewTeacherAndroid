package com.zidian.teacher.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.zidian.teacher.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * FragmentModule
 * Created by GongCheng on 2017/3/15.
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public Activity provideActivity() {
        return fragment.getActivity();
    }

    @Provides
    @ActivityContext
    public Context provideContext() {
        return fragment.getActivity();
    }

}
