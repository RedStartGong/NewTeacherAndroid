package com.zidian.teacher.ui.evaluate.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.StudentEva;
import com.zidian.teacher.presenter.StudentEvaPresenter;
import com.zidian.teacher.presenter.contract.StudentEvaContract;
import com.zidian.teacher.ui.evaluate.adapter.StudentEvaAdapter;
import com.zidian.teacher.ui.widget.RecyclerViewLinearDecoration;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class StudentEvaFragment extends BaseFragment implements StudentEvaContract.View{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Inject
    StudentEvaPresenter presenter;
    @Inject
    StudentEvaAdapter adapter;

    public static StudentEvaFragment newInstance() {

        Bundle args = new Bundle();

        StudentEvaFragment fragment = new StudentEvaFragment();
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
        checkNotNull(presenter);
        checkNotNull(adapter);
        errorView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new RecyclerViewLinearDecoration(activity,
                RecyclerViewLinearDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
        presenter.attachView(this);
        presenter.getStudentEva();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.deAttachView();
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
    public void showStudentEva(StudentEva studentEva) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        adapter.setData(studentEva);
    }
}
