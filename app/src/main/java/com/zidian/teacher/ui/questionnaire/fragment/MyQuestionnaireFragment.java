package com.zidian.teacher.ui.questionnaire.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.MyQuesList;
import com.zidian.teacher.presenter.MyQuestionnaireListPresenter;
import com.zidian.teacher.presenter.contract.MyQuestionnaireListContract;
import com.zidian.teacher.ui.questionnaire.adapter.MyQuesListAdapter;
import com.zidian.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/5/2.
 */

public class MyQuestionnaireFragment extends BaseFragment implements MyQuestionnaireListContract.View {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    TextView errorView;

    @Inject
    MyQuestionnaireListPresenter presenter;
    @Inject
    MyQuesListAdapter adapter;

    private FloatingActionButton fabAdd;

    private List<MyQuesList.ListBean> beanList;
    private int row = 1;

    public static MyQuestionnaireFragment newInstance() {

        Bundle args = new Bundle();

        MyQuestionnaireFragment fragment = new MyQuestionnaireFragment();
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
        checkNotNull(presenter);
        checkNotNull(adapter);
        beanList = new ArrayList<>();

        fabAdd = (FloatingActionButton) getParentFragment().getView().findViewById(R.id.fab_add_questionnaire);
        errorView.setVisibility(View.GONE);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 5) {
                    fabAdd.hide();
                } else {
                    fabAdd.show();
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                row = 1;
                presenter.getMyQues(String.valueOf(row));
            }

            @Override
            public void onLoadMore() {
                row++;
                presenter.getMyQues(String.valueOf(row));
            }
        });
        recyclerView.setAdapter(adapter);
        presenter.attachView(this);
        presenter.getMyQues(String.valueOf(row));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        loadingView.setVisibility(View.GONE);
        if (row == 1) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText(e.getMessage());
            beanList.clear();
            adapter.setData(beanList);
            recyclerView.refreshComplete();
        } else {
            errorView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmpty() {
        if (row == 1) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText("当前没有问卷");
            loadingView.setVisibility(View.GONE);
        } else {
            recyclerView.refreshComplete();
        }
    }

    @Override
    public void showMyQues(MyQuesList myQuesList) {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        recyclerView.refreshComplete();
        if (row == 1) {
            this.beanList.clear();
        }
        this.beanList.addAll(myQuesList.getList());
        if (myQuesList.getPages() == row) {
            recyclerView.noMoreLoading();
        }
        adapter.setData(beanList);
    }
}
