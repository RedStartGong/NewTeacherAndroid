package com.zidian.teacher.ui.mine.fragment;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.PersonInfo;
import com.zidian.teacher.presenter.PersonInfoPresenter;
import com.zidian.teacher.presenter.contract.PersonInfoContract;
import com.zidian.teacher.ui.main.activity.LoginActivity;
import com.zidian.teacher.ui.mine.activity.AboutActivity;
import com.zidian.teacher.ui.mine.activity.ChangeInfoActivity;
import com.zidian.teacher.ui.mine.activity.FeedbackActivity;
import com.zidian.teacher.util.ActManager;
import com.zidian.teacher.util.SharedPreferencesUtils;
import com.zidian.teacher.util.SnackbarUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * 个人中心
 * Created by GongCheng on 2017/3/15.
 */

public class MineFragment extends BaseFragment implements PersonInfoContract.View {
    @BindView(R.id.cim_portrait)
    CircleImageView cimPortrait;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_supervisor_eva_me)
    TextView tvSupervisorEvaMe;
    @BindView(R.id.tv_student_eva_me)
    TextView tvStudentEvaMe;
    @BindView(R.id.tv_colleague_eva_me)
    TextView tvColleagueEvaMe;
    @BindView(R.id.tv_supervisor_evaluate)
    TextView tvSupervisorEvaluate;
    @BindView(R.id.tv_colleague_evaluate)
    TextView tvColleagueEvaluate;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    PersonInfoPresenter presenter;

    @Inject
    ActManager actManager;

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

    //启动修改个人信息界面的requestCode
    private static final int INFO_CHANGED_CODE = 0;
    private PersonInfo personInfo;

    @OnClick({R.id.ll_information, R.id.ll_password, R.id.ll_logout, R.id.ll_feedback, R.id.ll_about})
    public void OnItemClick(View view) {
        switch (view.getId()) {
            case R.id.ll_information:
                if (personInfo == null) {
                    return;
                }
                Intent intent = new Intent(activity, ChangeInfoActivity.class);
                intent.putExtra("personInfo", personInfo);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivityForResult(intent, INFO_CHANGED_CODE,
                            ActivityOptions.makeSceneTransitionAnimation(activity,cimPortrait,"shareView" ).toBundle());
                } else {
                    startActivityForResult(intent, INFO_CHANGED_CODE);
                }
                break;
            case R.id.ll_password:
                start(ChangePasswordFragment.newInstance());
                break;
            case R.id.ll_logout:
                showExitDialog();
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(activity, FeedbackActivity.class));
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
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPersonInfo();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        checkNotNull(presenter);
        presenter.deAttachView();
    }

    /**
     * 根据返回值判断个人信息是否修改
     *
     * @param requestCode 请求code
     * @param resultCode  结果code
     * @param data        intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INFO_CHANGED_CODE && resultCode == RESULT_OK) {
            presenter.getPersonInfo();
        }
    }


    private void showExitDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("提示");
        builder.setMessage("确定要退出登录吗?");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                actManager.finishAllActivity();
                SharedPreferencesUtils.clearAll();
                startActivity(new Intent(activity, LoginActivity.class));
            }
        });
        builder.show();
    }

    @Override
    public void showError(Throwable e) {
        swipeRefreshLayout.setRefreshing(false);
        SnackbarUtils.showShort(cimPortrait, e.getMessage());
    }

    @Override
    public void showInfo(PersonInfo personInfo) {
        swipeRefreshLayout.setRefreshing(false);
        tvName.setText(personInfo.getName());
        tvStudentEvaMe.setText(String.valueOf(personInfo.getByStuEvalNum()));
        tvSupervisorEvaMe.setText(String.valueOf(personInfo.getBySupEvalNum()));
        tvColleagueEvaMe.setText(String.valueOf(personInfo.getByTchEvalNum()));
        tvColleagueEvaluate.setText(String.valueOf(personInfo.getToTchEvalNum()));
        tvSupervisorEvaluate.setText(String.valueOf(personInfo.getToSupEvalNum()));

        Glide.with(this).load(personInfo.getIconUrl())
                .error(R.drawable.ic_teacher)
                .into(cimPortrait);
        this.personInfo = personInfo;
    }
}
