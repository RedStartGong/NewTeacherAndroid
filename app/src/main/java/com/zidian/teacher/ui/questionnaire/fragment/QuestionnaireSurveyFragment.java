package com.zidian.teacher.ui.questionnaire.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.QuesSurveyList;
import com.zidian.teacher.presenter.QuestionnaireSurveyPresenter;
import com.zidian.teacher.presenter.contract.QuestionnaireSurveyContract;
import com.zidian.teacher.ui.questionnaire.adapter.QuesSurveyListAdapter;
import com.zidian.teacher.ui.widget.RecyclerViewLinearDecoration;
import com.zidian.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/5/2.
 */

public class QuestionnaireSurveyFragment extends BaseFragment implements QuestionnaireSurveyContract.View {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    TextView errorView;

    @Inject
    QuestionnaireSurveyPresenter presenter;
    @Inject
    QuesSurveyListAdapter adapter;

    private FloatingActionButton fabAdd;

    private int row = 1;
    private List<QuesSurveyList.QuestionnaireListBean> questionnaireListBeanList;

    public static QuestionnaireSurveyFragment newInstance() {

        Bundle args = new Bundle();

        QuestionnaireSurveyFragment fragment = new QuestionnaireSurveyFragment();
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

        errorView.setVisibility(View.GONE);
        questionnaireListBeanList = new ArrayList<>();
        fabAdd = (FloatingActionButton) getParentFragment().getView().findViewById(R.id.fab_add_questionnaire);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                row = 1;
                presenter.getQuestionnaireSurveyList(String.valueOf(row));
            }

            @Override
            public void onLoadMore() {
                row++;
                presenter.getQuestionnaireSurveyList(String.valueOf(row));
            }
        });
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
        recyclerView.setAdapter(adapter);
        presenter.attachView(this);
        presenter.getQuestionnaireSurveyList(String.valueOf(row));

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        recyclerView.refreshComplete();
        loadingView.setVisibility(View.GONE);
        if (row == 1) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText(e.getMessage());
            questionnaireListBeanList.clear();
            adapter.setData(questionnaireListBeanList);
        } else {
            errorView.setVisibility(View.GONE);
        }

    }

    @Override
    public void showEmpty() {
        if (row == 1) {
            loadingView.setVisibility(View.GONE);
            errorView.setVisibility(View.VISIBLE);
            errorView.setText("当前没有问卷");
        } else {
            recyclerView.refreshComplete();
        }
    }

    @Override
    public void showQuestionnaireSurveyList(QuesSurveyList quesSurveyList) {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        recyclerView.refreshComplete();
        if (row == 1) {
            recyclerView.refreshComplete();
            questionnaireListBeanList.clear();
        } else {
            recyclerView.setLoadMoreGone();
        }

        this.questionnaireListBeanList.addAll(quesSurveyList.getQuestionnaireList());
        if (quesSurveyList.getPages() == row) {
            recyclerView.noMoreLoading();
        }
        adapter.setData(this.questionnaireListBeanList);
    }
}
