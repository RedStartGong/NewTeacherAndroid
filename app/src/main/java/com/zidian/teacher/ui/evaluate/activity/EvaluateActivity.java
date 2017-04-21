package com.zidian.teacher.ui.evaluate.activity;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.EvaluateTag;
import com.zidian.teacher.presenter.EvaluatePresenter;
import com.zidian.teacher.presenter.contract.EvaluateContract;
import com.zidian.teacher.recyclerviewpager.recycleview.RecyclerViewPager;
import com.zidian.teacher.ui.evaluate.adapter.EvaluateAdapter;
import com.zidian.teacher.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/20.
 */

public class EvaluateActivity extends BaseActivity implements EvaluateContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    RecyclerViewPager viewPager;

    @Inject
    EvaluatePresenter presenter;

    private EvaluateAdapter adapter;
    private List<EvaluateTag> evaluateTags;
    private ProgressDialog progressDialog;

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
        evaluateTags = new ArrayList<>();
        toolbar.setTitle("标签评价");
        setToolbarBack(toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中...");

        presenter.attachView(this);
        presenter.getEvaluateTags();
        initRecyclerView();
    }

    private void initRecyclerView() {

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        viewPager.setLayoutManager(layout);
        viewPager.setHasFixedSize(true);
        viewPager.setLongClickable(true);
        viewPager.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int childCount = viewPager.getChildCount();
                int width = viewPager.getChildAt(0).getWidth();
                int padding = (viewPager.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    ;
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
        viewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
            }
        });

        viewPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (viewPager.getChildCount() < 3) {
                    if (viewPager.getChildAt(1) != null) {
                        if (viewPager.getCurrentPosition() == 0) {
                            View v1 = viewPager.getChildAt(1);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = viewPager.getChildAt(0);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (viewPager.getChildAt(0) != null) {
                        View v0 = viewPager.getChildAt(0);
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    if (viewPager.getChildAt(2) != null) {
                        View v2 = viewPager.getChildAt(2);
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
        getMenuInflater().inflate(R.menu.menu_evaluate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        presenter.evaluate("0", "2", );
        return super.onOptionsItemSelected(item);
    }

    /**
     * 获取评价的内容
     */
    private String getEvaluateLabel() {
        Map<Integer, String> map = new HashMap<>();
        map = adapter.getSlect();
        String result = "";

        result = map.get(0);

        for (int i = 1; i < evaluateTags.size(); i++) {

            if (map.get(i) == null) {
                result = null;
                break;
            }
            result += "," + map.get(i);
        }
        return result;
    }

    @Override
    public void showError(Throwable e) {
        SnackbarUtils.showShort(toolbar, e.getMessage());
        progressDialog.dismiss();
    }

    @Override
    public void showEvaluateTags(List<EvaluateTag> evaluateTags) {
        this.evaluateTags = evaluateTags;
        adapter = new EvaluateAdapter(this, evaluateTags);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showTagsError(Throwable e) {
        progressDialog.dismiss();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "评价成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
