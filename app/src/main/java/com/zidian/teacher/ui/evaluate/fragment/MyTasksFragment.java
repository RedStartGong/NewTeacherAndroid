package com.zidian.teacher.ui.evaluate.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.MyTask;
import com.zidian.teacher.presenter.MyTaskPresenter;
import com.zidian.teacher.presenter.contract.MyTaskContract;
import com.zidian.teacher.ui.evaluate.activity.MyTaskActivity;
import com.zidian.teacher.ui.evaluate.adapter.MyTaskAdapter;
import com.zidian.teacher.ui.widget.RecyclerViewLinearDecoration;
import com.zidian.xrecyclerview.XRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * 我的任务 Fragment
 * Created by GongCheng on 2017/4/12.
 */

public class MyTasksFragment extends BaseFragment implements MyTaskContract.View {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Inject
    MyTaskPresenter presenter;
    @Inject
    MyTaskAdapter adapter;

    private String taskType;

    public static MyTasksFragment newInstance(@MyTaskActivity.TaskType String type) {

        Bundle args = new Bundle();

        MyTasksFragment fragment = new MyTasksFragment();
        fragment.taskType = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_base_recycler_view;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        errorView.setVisibility(View.GONE);
        checkNotNull(adapter);
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getTasks(taskType);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.addItemDecoration(new RecyclerViewLinearDecoration(activity,
                RecyclerViewLinearDecoration.VERTICAL_LIST));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getTasks(taskType);
            }

            @Override
            public void onLoadMore() {

            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(Throwable e) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        recyclerView.refreshComplete();
    }

    @Override
    public void showTasks(List<MyTask> tasks) {
        loadingView.setVisibility(View.GONE);
        adapter.setTasks(tasks);
        recyclerView.refreshComplete();
    }

    @Override
    public void showEmpty() {
        loadingView.setVisibility(View.GONE);
        errorView.setText("当前没有任务");
        adapter.setTasks(null);
        recyclerView.refreshComplete();
    }
}
