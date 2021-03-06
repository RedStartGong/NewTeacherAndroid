package com.zidian.teacher.ui.evaluate.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.College;
import com.zidian.teacher.model.entity.remote.EvaTeacher;
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
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.tv_notice)
    TextView tv_notice;

    @Inject
    InviteSelectTeacherPresenter presenter;
    @Inject
    InviteTeacherAdapter adapter;

    private MaterialDialog progressDialog;

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
        progressDialog = new MaterialDialog.Builder(this)
                .progress(true, 10)
                .content("加载中...")
                .build();
        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(this)
                //a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .build();
        recyclerView.setLayoutManager(chipsLayoutManager);
        recyclerView.addItemDecoration(new SpacingItemDecoration(15, 10));
        recyclerView.setAdapter(adapter);

        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getColleges();
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<College>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, College item) {
                int collegeId = item.getCollegeId();
                presenter.getTeachers(collegeId);
            }
        });

    }


    /**
     * 提交
     */
    @OnClick(R.id.fab_submit)
    public void submit() {
        if (TextUtils.isEmpty(adapter.getTeachers())) {
            SnackbarUtils.showShort(spinner, "请选择教师");
            return;
        }
        if (adapter.getTeachers().equals("30")) {
            SnackbarUtils.showShort(spinner, "选择的教师人数不得超多30");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("teachers", adapter.getTeachers());
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 返回按钮
     */
    @OnClick(R.id.iv_back)
    public void back() {
        onBackPressedSupport();
    }

    /**
     * 添加教师
     *
     * @param teachers List<InviteTeacher>
     */
    private void addTeachers(final List<EvaTeacher> teachers) {
        final Integer[] selected = new Integer[30];
        final List<EvaTeacher> addTeachers = new ArrayList<>();
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
        SnackbarUtils.showShort(spinner, e.getMessage());
    }

    @Override
    public void showLoading() {
        tv_notice.setVisibility(View.GONE);
        progressDialog.show();
    }

    @Override
    public void showEmpty() {
        progressDialog.dismiss();
        SnackbarUtils.showShort(spinner, "没有搜索到相关教师");
    }

    @Override
    public void showColleges(List<College> colleges) {
        spinner.setItems(colleges);
    }

    @Override
    public void showTeachers(List<EvaTeacher> teachers) {
        addTeachers(teachers);
        tv_notice.setVisibility(View.GONE);
    }

}
