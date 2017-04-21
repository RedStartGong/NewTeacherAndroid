package com.zidian.teacher.ui.evaluate.fragment;

import android.content.Intent;
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
import com.zidian.teacher.ui.evaluate.activity.EvaluateActivity;
import com.zidian.teacher.ui.evaluate.activity.MyTaskActivity;
import com.zidian.teacher.ui.evaluate.adapter.MyTaskAdapter;
import com.zidian.teacher.ui.evaluate.listener.MyTaskOnClickListener;
import com.zidian.teacher.ui.widget.RecyclerViewLinearDecoration;
import com.zidian.teacher.util.SnackbarUtils;
import com.zidian.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
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
    private List<MyTask> myTasks;

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
        myTasks = new ArrayList<>();
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
        itemClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.deAttachView();
    }

    /**
     * 处理 adapter 点击事件
     * 修改为什么状态：0为请求发出，待确认；1为同意，待评价；2为被拒绝
     */
    private void itemClicks() {
        adapter.setMyTaskOnClickListener(new MyTaskOnClickListener() {
            @Override
            public void evaluate(int position) {
                SnackbarUtils.showShort(errorView, position + "");
                Intent intent = new Intent(activity, EvaluateActivity.class);
                intent.putExtra("teacherType", myTasks.get(position).getEvaluationType());
                intent.putExtra("toTeacherId", myTasks.get(position).getToTeacherId());
                intent.putExtra("recordId", myTasks.get(position).getRecordId());
                startActivity(intent);
            }

            @Override
            public void reject(int position) {
                presenter.changeEvaState(String.valueOf(myTasks.get(position).getRecordId()),"2");
                adapter.removeTask(position);
            }

            @Override
            public void agree(int position) {
                presenter.changeEvaState(String.valueOf(myTasks.get(position).getRecordId()),"1");
                adapter.removeTask(position);
            }

            @Override
            public void colleagueCheck(int position) {
                SnackbarUtils.showShort(errorView, position + "");
            }

            @Override
            public void supervisorCheck(int position) {
                SnackbarUtils.showShort(errorView, position + "");
            }

            @Override
            public void supervisorConfirm(int position) {

            }

            @Override
            public void supervisorEvaluate(int position) {

            }
        });
    }

    @Override
    public void showError(Throwable e) {
        myTasks.clear();
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        recyclerView.refreshComplete();
    }

    @Override
    public void showTasks(List<MyTask> tasks) {
        myTasks = tasks;
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        adapter.setTasks(tasks);
        recyclerView.refreshComplete();
    }

    @Override
    public void showEmpty() {
        myTasks.clear();
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText("当前没有任务");
        adapter.setTasks(null);
        recyclerView.refreshComplete();
    }

    @Override
    public void showChangeStateSucceed() {
        SnackbarUtils.showShort(errorView, "操作成功");
    }

    @Override
    public void showChangeStateError(Throwable e) {
        SnackbarUtils.showShort(errorView, e.getMessage());
        presenter.getTasks(taskType);
    }
}
