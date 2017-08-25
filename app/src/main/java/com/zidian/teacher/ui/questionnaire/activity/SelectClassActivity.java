package com.zidian.teacher.ui.questionnaire.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.SelectClass;
import com.zidian.teacher.presenter.SelectClassPresenter;
import com.zidian.teacher.presenter.contract.SelectClassContract;
import com.zidian.teacher.ui.questionnaire.adapter.SelectClassAdapter;
import com.zidian.teacher.ui.questionnaire.bean.ClassBean;
import com.zidian.teacher.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/5/8.
 */

public class SelectClassActivity extends BaseActivity implements SelectClassContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Inject
    SelectClassPresenter presenter;

    private SelectClassAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_select_class;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("请选择班级");
        setToolbarBack(toolbar);
        errorView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        presenter.attachView(this);
        presenter.getAllClasses();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return true;
    }

    /**
     * 菜单点击事件
     *
     * @param item menuItem
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (adapter == null) {
            SnackbarUtils.showShort(toolbar, "无可选班级");
            return true;
        }
        if (TextUtils.isEmpty(adapter.getClasses())){
            SnackbarUtils.showShort(toolbar, "请选择班级");
            return true;
        }
        Intent intent = new Intent();
        intent.putExtra("classes", adapter.getClasses());
        setResult(RESULT_OK, intent);
        finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        loadingView.setVisibility(View.GONE);

    }

    @Override
    public void showClasses(List<SelectClass> classes) {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        adapter = new SelectClassAdapter(classes);
        recyclerView.setAdapter(adapter);
    }
}
