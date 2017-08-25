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
import com.zidian.teacher.model.entity.remote.QuesSurveyList;
import com.zidian.teacher.presenter.QuestionnaireSurveyPresenter;
import com.zidian.teacher.presenter.contract.QuestionnaireSurveyContract;
import com.zidian.teacher.ui.questionnaire.adapter.QuesSurveyListAdapter;
import com.zidian.teacher.ui.questionnaire.event.QuesSurveyFinishEvent;
import com.zidian.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * 我需要填写的问卷 fragment
 * Created by GongCheng on 2017/5/2.
 */

public class QuestionnaireSurveyFragment extends BaseFragment{
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    TextView errorView;

    @Inject
    QuesSurveyListAdapter adapter;

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
        checkNotNull(adapter);

        errorView.setText("当前没有您需要填写的问卷");
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
