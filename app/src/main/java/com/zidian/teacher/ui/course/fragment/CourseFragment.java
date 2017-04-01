package com.zidian.teacher.ui.course.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/3/15.
 */

public class CourseFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static CourseFragment newInstance() {

        Bundle args = new Bundle();

        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle(R.string.main_course);

    }


}
