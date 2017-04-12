package com.zidian.teacher.ui.evaluate.activity;

import android.support.v7.widget.Toolbar;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/12.
 */

public class ColleagueEvaActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_colleague_eva;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle("同行评价");
        setToolbarBack(toolbar);
    }
}
