package com.zidian.teacher.ui.evaluate.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by GongCheng on 2017/4/12.
 */

public class ColleagueEvaActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_evaluate_bg)
    ImageView ivEvaluateBg;

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
        toolbar.setTitle(R.string.colleague_evaluate);
        setToolbarBack(toolbar);
        Glide.with(this).load(R.drawable.bg_evaluate).into(ivEvaluateBg);
    }

    /**
     * 邀请
     */
    @OnClick(R.id.btn_invite)
    public void invite() {
        startActivity(new Intent(this, InviteActivity.class));
    }

    /**
     * 评价他人
     */
    @OnClick(R.id.btn_evaluate)
    public void evaluate() {
        startActivity(new Intent(this, ApplyToEvaActivity.class));
    }
}
