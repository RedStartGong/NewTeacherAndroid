package com.zidian.teacher.ui.evaluate.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.InviteTeacher;
import com.zidian.teacher.presenter.InviteSelectTeacherPresenter;
import com.zidian.teacher.presenter.contract.InviteSelectTeacherContract;
import com.zidian.teacher.ui.evaluate.adapter.InviteTeacherAdapter;
import com.zidian.teacher.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zidian.teacher.util.Preconditions.checkNotNull;


/**
 * 邀请老师评价我
 * Created by GongCheng on 2017/4/14.
 */

public class InviteSelectTeacherActivity extends BaseActivity implements InviteSelectTeacherContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.tv_notice)
    TextView tv_notice;

    @Inject
    InviteSelectTeacherPresenter presenter;
    @Inject
    InviteTeacherAdapter adapter;

    private ProgressDialog progressDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_invite_select_teacher;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("选择您想邀请的老师");
        setToolbarBack(toolbar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中...");
        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(this)
                //a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .build();
        recyclerView.setLayoutManager(chipsLayoutManager);
        recyclerView.addItemDecoration(new SpacingItemDecoration(15, 10));
        recyclerView.setAdapter(adapter);

        checkNotNull(presenter);
        presenter.attachView(this);
        searchView.setHint("教师的名字、教师工号或学院名");
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getInviteTeachers(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        searchView.setMenuItem(menuItem);

        return true;
    }

    /**
     * 提交
     */
    @OnClick(R.id.fab_submit)
    public void submit() {
        if (TextUtils.isEmpty(adapter.getTeachers())) {
            SnackbarUtils.showShort(toolbar, "请选择教师");
            return;
        }
        if (adapter.getTeachers().equals("30")) {
            SnackbarUtils.showShort(toolbar, "选择的教师人数不得超多30");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("teachers", adapter.getTeachers());
        setResult(RESULT_OK,intent);
        finish();
    }

    /**
     * 添加教师
     *
     * @param teachers List<InviteTeacher>
     */
    private void addTeachers(final List<InviteTeacher> teachers) {
        final Integer[] selected = new Integer[30];
        final List<InviteTeacher> addTeachers = new ArrayList<>();
        new MaterialDialog.Builder(this).title("请选择教师")
                .items(teachers)
                .itemsCallbackMultiChoice(new Integer[]{}, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        for (int i = 0; i < which.length; i++) {
                            selected[i] = which[i];
                        }
                        addTeachers.clear();
                        for (int i = 0; i < which.length; i++) {
                            addTeachers.add(teachers.get(selected[i]));
                        }
                        adapter.addTeachers(addTeachers);

                        return true;
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.clearSelectedIndices();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .positiveText("选取")
                .autoDismiss(false)
                .neutralText("清除")
                .show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        progressDialog.dismiss();
        SnackbarUtils.showShort(toolbar, e.getMessage());
    }

    @Override
    public void showLoading() {
        tv_notice.setVisibility(View.GONE);
        progressDialog.show();
    }

    @Override
    public void showEmpty() {
        progressDialog.dismiss();
        SnackbarUtils.showShort(toolbar, "没有搜索到相关教师");
    }

    @Override
    public void showInviteTeachers(List<InviteTeacher> teachers) {
        progressDialog.dismiss();
        addTeachers(teachers);
    }
}
