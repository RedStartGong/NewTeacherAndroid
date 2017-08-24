package com.zidian.teacher.ui.evaluate.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.CustomEva;
import com.zidian.teacher.presenter.CustomEvaPresenter;
import com.zidian.teacher.presenter.contract.CustomEvaContract;
import com.zidian.teacher.ui.evaluate.adapter.CustomEvaAdapter;
import com.zidian.teacher.ui.widget.XRecyclerViewLinearDecoration;
import com.zidian.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * 自定义评价Fragment
 * Created by GongCheng on 2017/4/27.
 */

public class CustomEvaFragment extends BaseFragment implements CustomEvaContract.View{
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Inject
    CustomEvaPresenter presenter;
    @Inject
    CustomEvaAdapter adapter;

    private int startRow = 1;
    private List<CustomEva> list;

    public static CustomEvaFragment newInstance() {

        Bundle args = new Bundle();

        CustomEvaFragment fragment = new CustomEvaFragment();
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
        startRow = 1;
        list = new ArrayList<>();
        errorView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new XRecyclerViewLinearDecoration(context, XRecyclerViewLinearDecoration.VERTICAL_LIST));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                startRow = 1;
                presenter.getCustomEva(startRow);
            }

            @Override
            public void onLoadMore() {
                startRow ++;
                presenter.getCustomEva(startRow);
            }
        });
        recyclerView.setAdapter(adapter);
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getCustomEva(startRow);
    }

    @Override
    public void showError(Throwable e) {
        recyclerView.refreshComplete();
        if (startRow == 1) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText(e.getMessage());
            this.list.clear();
            adapter.setData(this.list);
        }  else {
            errorView.setVisibility(View.GONE);
            recyclerView.noMoreLoading();
        }
        loadingView.setVisibility(View.GONE);

    }


    @Override
    public void showEmpty() {
        if (startRow == 1) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText("当前没有评价");
            loadingView.setVisibility(View.GONE);
        } else {
            recyclerView.refreshComplete();
            recyclerView.noMoreLoading();
        }
    }

    @Override
    public void showCustomEva(List<CustomEva> customEvas) {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        recyclerView.refreshComplete();
        if (startRow == 1) {
            this.list.clear();
        }
        this.list.addAll(customEvas);
        adapter.setData(this.list);
    }

}
