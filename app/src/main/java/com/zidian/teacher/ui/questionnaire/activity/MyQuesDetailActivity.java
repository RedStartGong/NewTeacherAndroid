package com.zidian.teacher.ui.questionnaire.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.MyQuesDetail;
import com.zidian.teacher.presenter.MyQuesDetailPresenter;
import com.zidian.teacher.presenter.contract.MyQuesDetailContract;
import com.zidian.teacher.ui.questionnaire.adapter.MyQuesDetailAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/5/4.
 */

public class MyQuesDetailActivity extends BaseActivity implements MyQuesDetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_question_detail_title)
    TextView tvQuestionDetailTitle;
    @BindView(R.id.tv_question_detail_total)
    TextView tvQuestionDetailTotal;
    @BindView(R.id.rv_question_detail_list)
    RecyclerView rvQuestionDetailList;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    MaterialProgressBar loadingView;

    @Inject
    MyQuesDetailPresenter presenter;
    @Inject
    MyQuesDetailAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_ques_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);
        checkNotNull(adapter);

        errorView.setVisibility(View.GONE);
        Intent intent = getIntent();
        String questionnaireId = String.valueOf(intent.getIntExtra("questionnaireId", 0));
        String questionnaireTitle = intent.getStringExtra("questionnaireTitle");
        int completeCount = intent.getIntExtra("completeCount", 0);

        toolbar.setTitle("问卷详情");
        setToolbarBack(toolbar);
        tvQuestionDetailTitle.setText(questionnaireTitle);
        tvQuestionDetailTotal.setText(getString(R.string.my_ques_detail_complete,completeCount));
        rvQuestionDetailList.setLayoutManager(new LinearLayoutManager(this));
        rvQuestionDetailList.setAdapter(adapter);
        presenter.attachView(this);
        presenter.getMyQuesDetail(questionnaireId);
    }

    @Override
    public void showError(Throwable e) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showMyQuesDetail(List<MyQuesDetail> myQuesDetails) {
        loadingView.setVisibility(View.GONE);
        adapter.setData(myQuesDetails);
    }
}
