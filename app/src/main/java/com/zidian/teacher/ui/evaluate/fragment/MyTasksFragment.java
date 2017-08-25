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
import com.zidian.teacher.ui.evaluate.activity.CheckColleagueEvaActivity;
import com.zidian.teacher.ui.evaluate.activity.CheckSupervisorEvaActivity;
import com.zidian.teacher.ui.evaluate.activity.EvaluateActivity;
import com.zidian.teacher.ui.evaluate.activity.MyTaskActivity;
import com.zidian.teacher.ui.evaluate.adapter.MyTaskAdapter;
import com.zidian.teacher.ui.evaluate.event.FeedbackEvent;
import com.zidian.teacher.ui.evaluate.listener.MyTaskOnClickListener;
import com.zidian.teacher.util.SnackbarUtils;
import com.zidian.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    private int taskType;
    private List<MyTask> myTasks;
    private static final int REQUEST_EVALUATE = 1;

    public static MyTasksFragment newInstance(@MyTaskActivity.TaskType int type) {

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
        EventBus.getDefault().register(this);
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
        EventBus.getDefault().unregister(this);
    }

    /**
     * {@link EventBus} 接收event刷新界面
     *
     * @param event {@link FeedbackEvent}
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void feedbackSuccess(FeedbackEvent event) {
        if (event.isSuccess()) {
            myTasks.clear();
            adapter.setTasks(myTasks);
            presenter.getTasks(taskType);
        }
    }

    /**
     * 处理 adapter 点击事件
     * 修改为什么状态：0为请求发出，待确认；1为同意，待评价；2为被拒绝
     */
    private void itemClicks() {
        adapter.setMyTaskOnClickListener(new MyTaskOnClickListener() {
            @Override
            public void evaluate(int position) {
                Intent intent = new Intent(activity, EvaluateActivity.class);
                intent.putExtra("teacherType", myTasks.get(position).getEvaluateType());
                intent.putExtra("toTeacherId", myTasks.get(position).getAnotherTeacher());
                intent.putExtra("requestEvalMessageId", myTasks.get(position).getRequestEvalMessageId());
                intent.putExtra("evaluateType", myTasks.get(position).getEvaluateType());
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_EVALUATE);
            }

            @Override
            public void reject(int position) {
                presenter.changeEvaState(myTasks.get(position).getRequestEvalMessageId(), 3);
                adapter.removeTask(position);
            }

            @Override
            public void agree(int position) {
                presenter.changeEvaState(myTasks.get(position).getRequestEvalMessageId(), 1);
                adapter.removeTask(position);
            }

            @Override
            public void colleagueCheck(int position) {
                Intent intent = new Intent(activity, CheckColleagueEvaActivity.class);
                intent.putExtra("requestEvalMessageId", myTasks.get(position).getRequestEvalMessageId());
                startActivity(intent);
            }

            @Override
            public void supervisorCheck(int position) {
                Intent intent = new Intent(activity, CheckSupervisorEvaActivity.class);
                intent.putExtra("teacherType", myTasks.get(position).getEvaluateType());
                intent.putExtra("toTeacherId", myTasks.get(position).getAnotherTeacher());
                intent.putExtra("requestEvalMessageId", myTasks.get(position).getRequestEvalMessageId());
                intent.putExtra("evaluateType", myTasks.get(position).getEvaluateType());
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_EVALUATE);
            }

            @Override
            public void supervisorConfirm(int position) {
                Intent intent = new Intent(activity, CheckSupervisorEvaActivity.class);
                intent.putExtra("teacherType", myTasks.get(position).getEvaluateType());
                intent.putExtra("toTeacherId", myTasks.get(position).getAnotherTeacher());
                intent.putExtra("requestEvalMessageId", myTasks.get(position).getRequestEvalMessageId());
                intent.putExtra("evaluateType", myTasks.get(position).getEvaluateType());
                intent.putExtra("position", position);
                if (myTasks.get(position).getMyRole() == 1) {
                    intent.putExtra("needConfirm", true);
                }
                startActivityForResult(intent, REQUEST_EVALUATE);
            }

            @Override
            public void supervisorEvaluate(int position) {
                Intent intent = new Intent(activity, EvaluateActivity.class);
                intent.putExtra("teacherType", myTasks.get(position).getEvaluateType());
                intent.putExtra("toTeacherId", myTasks.get(position).getAnotherTeacher());
                intent.putExtra("requestEvalMessageId", myTasks.get(position).getRequestEvalMessageId());
                intent.putExtra("evaluateType", myTasks.get(position).getEvaluateType());
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_EVALUATE);
            }
        });
    }

    /**
     * 根据返回值判断是否刷新界面
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EVALUATE && resultCode == RESULT_OK) {
            adapter.removeTask(data.getIntExtra("position", 0));
            presenter.getTasks(taskType);
        }
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
