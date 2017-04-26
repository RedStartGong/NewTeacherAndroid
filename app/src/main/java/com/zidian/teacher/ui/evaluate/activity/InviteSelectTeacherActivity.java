package com.zidian.teacher.ui.evaluate.activity;

import android.content.Intent;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.gravity.IChildGravityResolver;
import com.beloo.widget.chipslayoutmanager.layouter.breaker.IRowBreaker;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.InviteTeacher;
import com.zidian.teacher.presenter.InviteSelectTeacherPresenter;
import com.zidian.teacher.presenter.contract.InviteSelectTeacherContract;
import com.zidian.teacher.ui.evaluate.adapter.InviteTeacherAdapter;
import com.zidian.teacher.ui.widget.RecyclerViewLinearDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

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
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @Inject
    InviteSelectTeacherPresenter presenter;

    private InviteTeacherAdapter adapter;

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
        errorView.setVisibility(View.GONE);
        adapter = new InviteTeacherAdapter();
        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(this)
                //set vertical gravity for all items in a row. Default = Gravity.CENTER_VERTICAL
                .setChildGravity(Gravity.CENTER_VERTICAL)
                //whether RecyclerView can scroll. TRUE by default
                .setScrollingEnabled(true)
                //set maximum views count in a particular row
                .setMaxViewsInRow(3)
                //set gravity resolver where you can determine gravity for item in position.
                //This method have priority over previous one
                .setGravityResolver(new IChildGravityResolver() {
                    @Override
                    public int getItemGravity(int position) {
                        return Gravity.CENTER;
                    }
                })
                //you are able to break row due to your conditions. Row breaker should return true for that views
                .setRowBreaker(new IRowBreaker() {
                    @Override
                    public boolean isItemBreakRow(@IntRange(from = 0) int position) {
                        return position == 6 || position == 11 || position == 2;
                    }
                })
                //a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                // row strategy for views in completed row, could be STRATEGY_DEFAULT, STRATEGY_FILL_VIEW,
                //STRATEGY_FILL_SPACE or STRATEGY_CENTER
                .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                // whether strategy is applied to last row. FALSE by default
                .withLastRow(true)
                .build();
        recyclerView.setLayoutManager(chipsLayoutManager);
        recyclerView.setAdapter(adapter);

        //点击 item 选择需要返回的教师
        adapter.setOnItemClickListener(new InviteTeacherAdapter.OnItemClickListener() {
            @Override
            public void onClick(InviteTeacher teacher) {
                Intent intent = new Intent();
                intent.putExtra("teacher", teacher);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        checkNotNull(presenter);
        presenter.attachView(this);
        searchView.setHint("请输入教师的名字、教师工号或学院名,最多30名");
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
                        dialog.dismiss();
                        return true;
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.clearSelectedIndices();
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
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        adapter.setTeachers(null);
    }

    @Override
    public void showLoading() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText("暂无教师");
        adapter.setTeachers(null);
    }

    @Override
    public void showInviteTeachers(List<InviteTeacher> teachers) {
        errorView.setVisibility(View.GONE);
        addTeachers(teachers);
    }
}
