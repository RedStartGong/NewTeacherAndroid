package com.zidian.teacher.di.componet;


import com.zidian.teacher.di.PerFragment;
import com.zidian.teacher.di.module.FragmentModule;

import dagger.Component;

/**
 * Created by GongCheng on 2017/3/15.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


}
