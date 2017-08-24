package com.zidian.teacher.ui.evaluate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.ui.evaluate.activity.ColleagueEvaActivity;
import com.zidian.teacher.ui.evaluate.activity.MyEvaluateActivity;
import com.zidian.teacher.ui.evaluate.activity.MyTaskActivity;
import com.zidian.teacher.ui.evaluate.activity.SupervisorEvaActivity;
import com.zidian.teacher.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 教评
 * Created by GongCheng on 2017/3/15.
 */

public class EvaluateFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_supervisor_evaluate)
    LinearLayout llSupervisorEvaluate;

    public static EvaluateFragment newInstance() {

        Bundle args = new Bundle();

        EvaluateFragment fragment = new EvaluateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_evaluate;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle(R.string.evaluate_manage);
        //如果是教师角色是普通老师则隐藏督导评价
        if (SharedPreferencesUtils.getTeacherType() == 1) {
            llSupervisorEvaluate.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.ll_may_task, R.id.ll_my_evaluate, R.id.ll_colleague_evaluate, R.id.ll_supervisor_evaluate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_may_task:
                startActivity(new Intent(activity, MyTaskActivity.class));
                break;
            case R.id.ll_my_evaluate:
                startActivity(new Intent(activity, MyEvaluateActivity.class));
                break;
            case R.id.ll_colleague_evaluate:
                startActivity(new Intent(activity, ColleagueEvaActivity.class));
                break;
            case R.id.ll_supervisor_evaluate:
                startActivity(new Intent(activity, SupervisorEvaActivity.class));
                break;
        }
    }
}
