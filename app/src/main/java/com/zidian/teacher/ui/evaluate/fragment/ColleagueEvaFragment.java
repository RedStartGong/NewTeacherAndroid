package com.zidian.teacher.ui.evaluate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.ColleagueEva;
import com.zidian.teacher.presenter.ColleagueEvaPresenter;
import com.zidian.teacher.presenter.contract.ColleagueEvaContract;
import com.zidian.teacher.ui.evaluate.activity.ColleagueEvaTwoIndexActivity;
import com.zidian.teacher.ui.evaluate.adapter.ColleagueEvaAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * 我的评价-督导/同行评价 fragment
 * Created by GongCheng on 2017/4/27.
 */

public class ColleagueEvaFragment extends BaseFragment implements ColleagueEvaContract.View{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    TextView errorView;

    @Inject
    ColleagueEvaPresenter presenter;
    @Inject
    ColleagueEvaAdapter adapter;

    private String evaluateType;

    public static ColleagueEvaFragment newInstance(String evaluateType) {

        Bundle args = new Bundle();

        ColleagueEvaFragment fragment = new ColleagueEvaFragment();
        fragment.evaluateType = evaluateType;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_base_eva;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        errorView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new ColleagueEvaAdapter.OnItemClickListener() {
            @Override
            public void onClick(String indexName, float indexScore) {
                Intent intent = new Intent(getContext(), ColleagueEvaTwoIndexActivity.class);
                intent.putExtra("indexName", indexName);
                intent.putExtra("evaluateType", evaluateType);
                intent.putExtra("indexScore", indexScore);
                startActivity(intent);
            }
        });
        checkNotNull(presenter);
        checkNotNull(adapter);
        presenter.attachView(this);
        presenter.getColleagueEva(evaluateType);
    }

    @Override
    public void showError(Throwable e) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showColleagueEva(List<ColleagueEva> colleagueEvaList) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        adapter.setData(colleagueEvaList);
    }
}
