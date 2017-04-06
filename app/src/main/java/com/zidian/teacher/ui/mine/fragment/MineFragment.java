package com.zidian.teacher.ui.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.PersonInfo;
import com.zidian.teacher.presenter.PersonInfoPresenter;
import com.zidian.teacher.presenter.contract.PersonInfoContract;
import com.zidian.teacher.ui.mine.activity.AboutActivity;
import com.zidian.teacher.ui.mine.activity.ChangePasswordActivity;
import com.zidian.teacher.util.SnackbarUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/3/15.
 */

public class MineFragment extends BaseFragment implements PersonInfoContract.View {
    @BindView(R.id.cim_portrait)
    CircleImageView cimPortrait;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_motto)
    TextView tvMotto;
    @BindView(R.id.tv_evaluate_count)
    TextView tvEvaluateCount;
    @BindView(R.id.tv_evaluated_count)
    TextView tvEvaluatedCount;

    @Inject
    PersonInfoPresenter presenter;

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

    @OnClick({R.id.ll_information, R.id.ll_password, R.id.ll_logout, R.id.ll_feedback, R.id.ll_about})
    public void OnItemClick(View view) {
        switch (view.getId()) {
            case R.id.ll_information:
                break;
            case R.id.ll_password:
                startActivity(new Intent(activity, ChangePasswordActivity.class));
                break;
            case R.id.ll_logout:
                break;
            case R.id.ll_feedback:
                break;
            case R.id.ll_about:
                startActivity(new Intent(activity, AboutActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getPersonInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        checkNotNull(presenter);
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        SnackbarUtils.showShort(cimPortrait, e.getMessage());
    }

    @Override
    public void showInfo(PersonInfo personInfo) {
        tvName.setText(personInfo.getName());
        tvMotto.setText(personInfo.getPersonSignature());
        tvEvaluatedCount.setText(String.valueOf(personInfo.getEvaluatedCount()));
        tvEvaluateCount.setText("0");
        Glide.with(this).load(personInfo.getPortrait())
                .placeholder(R.drawable.ic_teacher)
                .into(cimPortrait);
    }
}
