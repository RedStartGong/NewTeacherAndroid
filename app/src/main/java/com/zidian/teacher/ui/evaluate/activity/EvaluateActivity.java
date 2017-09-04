package com.zidian.teacher.ui.evaluate.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.EvaluateTag;
import com.zidian.teacher.presenter.EvaluatePresenter;
import com.zidian.teacher.presenter.contract.EvaluateContract;
import com.zidian.teacher.recyclerviewpager.recycleview.RecyclerViewPager;
import com.zidian.teacher.ui.evaluate.adapter.EvaluateAdapter;
import com.zidian.teacher.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 评价界面
 * Created by GongCheng on 2017/4/20.
 */

public class EvaluateActivity extends BaseActivity implements EvaluateContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_pager)
    RecyclerViewPager recyclerViewPager;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Inject
    EvaluatePresenter presenter;

    private EvaluateAdapter adapter;
    private EvaluateTag evaluateTag;
    private MaterialDialog progressDialog;
    private String teacherType;
    private int toTeacherId;
    private String recordId;
    int requestEvalMessageId;
    private int evaluateType;
    //评价的条目
    private int position;

    @Override
    protected int getLayout() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Intent intent = getIntent();
        teacherType = String.valueOf(intent.getIntExtra("teacherType", 0));
        toTeacherId = intent.getIntExtra("toTeacherId", 0);
        recordId = String.valueOf(intent.getIntExtra("recordId", 0));
        evaluateType = intent.getIntExtra("evaluateType", 0);
        position = intent.getIntExtra("position", 0);
        requestEvalMessageId = intent.getIntExtra("requestEvalMessageId", 0);
        errorView.setVisibility(View.GONE);
        toolbar.setTitle("标签评价");
        setSupportActionBar(toolbar);
        setToolbarBack(toolbar);

        progressDialog = new MaterialDialog.Builder(this)
                .progress(true, 10)
                .content("加载中...")
                .build();

        presenter.attachView(this);
        presenter.getEvaluateTags(requestEvalMessageId);
        initRecyclerView();
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
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_evaluate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getEvaluateLabel() == null) {
            SnackbarUtils.showShort(toolbar, "请左右滑动选择标签");
            return true;
        }
        if (getCustomEva().length() > 50) {
            SnackbarUtils.showShort(toolbar, "自定义评价不能超过50个字符");
            return true;
        }
        presenter.evaluate(requestEvalMessageId, toTeacherId, evaluateType, getEvaluateLabel(), getCustomEva());
        return super.onOptionsItemSelected(item);
    }

    /**
     * 获取评价的内容
     */
    private String getEvaluateLabel() {
        Map<Integer, String> map = adapter.getSelect();
        if (evaluateTag.getThreeIndexList() == null || evaluateTag.getThreeIndexList().size() != map.size()) {
            SnackbarUtils.showShort(toolbar, "请完成标签的选取");
            return null;
        } else {
            Gson gson = new Gson();
            List<String> contentList = new ArrayList<>();
            for (int i = 0; i < evaluateTag.getThreeIndexList().size(); i++) {
                contentList.add(map.get(i));
            }
            return gson.toJson(contentList);
        }
    }

    /**
     * 获取自定义评价
     *
     * @return
     */
    private String getCustomEva() {
        return adapter.getCustomEva();
    }

    @Override
    public void showError(Throwable e) {
        SnackbarUtils.showShort(toolbar, e.getMessage());

        loadingView.setVisibility(View.GONE);
        progressDialog.dismiss();
    }

    @Override
    public void showEvaluateTag(EvaluateTag evaluateTag) {
        progressDialog.dismiss();
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        this.evaluateTag = evaluateTag;
        if (evaluateType == 3){
            adapter = new EvaluateAdapter(this, evaluateTag, true);
        } else{
            adapter = new EvaluateAdapter(this, evaluateTag, false);
        }
        recyclerViewPager.setAdapter(adapter);
    }

    @Override
    public void showTagsError(Throwable e) {
        progressDialog.dismiss();
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        new MaterialDialog.Builder(this)
                .title("温馨提示")
                .content("评价成功!")
                .positiveText("确定")
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                })
                .show();
        //评价成功，返回RESULT_OK
        Intent intent = new Intent();
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
    }
}
