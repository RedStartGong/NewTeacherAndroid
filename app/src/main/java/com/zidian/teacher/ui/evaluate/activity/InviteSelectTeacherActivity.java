package com.zidian.teacher.ui.evaluate.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.InviteTeacher;
import com.zidian.teacher.presenter.InviteSelectTeacherPresenter;
import com.zidian.teacher.presenter.contract.InviteSelectTeacherContract;
import com.zidian.teacher.ui.evaluate.adapter.InviteTeacherAdapter;
import com.zidian.teacher.ui.widget.RecyclerViewLinearDecoration;
import com.zidian.xrecyclerview.XRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;


/**
 * 邀请老师评价我
 * Created by GongCheng on 2017/4/14.
 */

public class InviteSelectTeacherActivity extends BaseActivity implements InviteSelectTeacherContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    TextView errorView;

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
        toolbar.setTitle("选择您想邀请的老师");
        setToolbarBack(toolbar);
        errorView.setVisibility(View.GONE);
        adapter = new InviteTeacherAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyclerViewLinearDecoration(this, RecyclerViewLinearDecoration.HORIZONTAL_LIST));
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getInviteTeachers("经济与管理学院");
            }

            @Override
            public void onLoadMore() {

            }
        });
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
        presenter.getInviteTeachers("经济与管理学院");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        recyclerView.refreshComplete();
        adapter.setTeachers(null);
    }

    @Override
    public void showLoading() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText("暂无教师");
        adapter.setTeachers(null);
        recyclerView.refreshComplete();
    }

    @Override
    public void showInviteTeachers(List<InviteTeacher> teachers) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        adapter.setTeachers(teachers);
        recyclerView.refreshComplete();
    }
}
