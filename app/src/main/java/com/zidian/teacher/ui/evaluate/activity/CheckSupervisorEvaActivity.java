package com.zidian.teacher.ui.evaluate.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
import com.zidian.teacher.presenter.CheckSupervisorEvaPresenter;
import com.zidian.teacher.presenter.contract.CheckSupervisorEvaContract;
import com.zidian.teacher.recyclerviewpager.recycleview.RecyclerViewPager;
import com.zidian.teacher.ui.evaluate.adapter.SupervisorCheckAdapter;
import com.zidian.teacher.util.SnackbarUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/4/25.
 */

public class CheckSupervisorEvaActivity extends BaseActivity implements CheckSupervisorEvaContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_pager)
    RecyclerViewPager recyclerViewPager;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.ll_need_confirm)
    LinearLayout llNeedConfirm;

    @Inject
    CheckSupervisorEvaPresenter presenter;

    private ProgressDialog progressDialog;
    private String recordId;

    @Override
    protected int getLayout() {
        return R.layout.activity_check_supervisor_eva;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        Intent intent = getIntent();
        recordId = String.valueOf(intent.getIntExtra("recordId", 0));
        boolean needConfirm = intent.getBooleanExtra("needConfirm", false);
        toolbar.setTitle("督导评价");
        setToolbarBack(toolbar);
        errorView.setVisibility(View.GONE);
        if (!needConfirm) {
            llNeedConfirm.setVisibility(View.GONE);
        }
        //初始化recyclerViewPager
        initRecyclerView();
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getEvaTags(recordId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    private void initRecyclerView() {

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        recyclerViewPager.setLayoutManager(layout);
        recyclerViewPager.setHasFixedSize(true);
        recyclerViewPager.setLongClickable(true);
        recyclerViewPager.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int childCount = recyclerViewPager.getChildCount();
                int width = recyclerViewPager.getChildAt(0).getWidth();
                int padding = (recyclerViewPager.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);

                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });
        recyclerViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
            }
        });

        recyclerViewPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (recyclerViewPager.getChildCount() < 3) {
                    if (recyclerViewPager.getChildAt(1) != null) {
                        if (recyclerViewPager.getCurrentPosition() == 0) {
                            View v1 = recyclerViewPager.getChildAt(1);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = recyclerViewPager.getChildAt(0);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (recyclerViewPager.getChildAt(0) != null) {
                        View v0 = recyclerViewPager.getChildAt(0);
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    if (recyclerViewPager.getChildAt(2) != null) {
                        View v2 = recyclerViewPager.getChildAt(2);
                        v2.setScaleY(0.9f);
                        v2.setScaleX(0.9f);
                    }
                }

            }
        });
    }

    @OnClick(R.id.btn_raise_objection)
    public void raiseObjection() {
        Intent intent = new Intent(this, SupervisorFeedbackActivity.class);
        intent.putExtra("recordId", recordId);
        startActivity(intent);
    }

    @OnClick(R.id.btn_confirm)
    public void confirm() {
        new MaterialDialog.Builder(this)
                .title("提示")
                .content("是否对评价的结果无异议？")
                .positiveText("确定")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        presenter.confirm(recordId);
                    }
                })
                .show();
    }

    @Override
    public void showError(Throwable e) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
    }

    @Override
    public void showEvaTags(CheckSupervisorEva checkSupervisorEva) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        SupervisorCheckAdapter adapter = new SupervisorCheckAdapter(this, checkSupervisorEva.getMapList(),
                checkSupervisorEva.getEvaluateComment());
        recyclerViewPager.setAdapter(adapter);
    }

    @Override
    public void showFeedbackSucceed() {
        progressDialog.dismiss();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showFeedbackError(Throwable throwable) {
        progressDialog.dismiss();
        SnackbarUtils.showShort(toolbar, throwable.getMessage());
    }

}
