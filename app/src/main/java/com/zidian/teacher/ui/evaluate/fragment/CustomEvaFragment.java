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
import com.zidian.xrecyclerview.XRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
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

    private int startRow = 1;

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
        errorView.setVisibility(View.GONE);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                startRow = 1;
                presenter.getCustomEva(String.valueOf(startRow));
            }

            @Override
            public void onLoadMore() {
                startRow ++;
                presenter.getCustomEva(String.valueOf(startRow));
            }
        });
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getCustomEva(String.valueOf(startRow));
    }

    @Override
    public void showError(Throwable e) {
        loadingView.setVisibility(View.GONE);
        if (startRow == 1) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText(e.getMessage());
        }
    }

    @Override
    public void showLoading() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void loadingMore() {

    }

    @Override
    public void showCustomEva(CustomEva customEva) {
        CustomEvaAdapter adapter = new CustomEvaAdapter(activity, customEva.getList());
        loadingView.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
    }
}
