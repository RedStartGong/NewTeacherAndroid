package com.zidian.teacher.ui.questionnaire.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.di.ActivityContext;
import com.zidian.teacher.model.entity.remote.QuesSurveyList;
import com.zidian.teacher.ui.questionnaire.activity.QuesSurveyDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 问卷调查 adapter
 * Created by GongCheng on 2017/4/5.
 */

public class QuesSurveyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QuesSurveyList.QuestionnaireListBean> data;
    private Context context;

    @Inject
    public QuesSurveyListAdapter(@ActivityContext Context context) {
        this.context =context;
        data = new ArrayList<>();
    }

    public void setData(List<QuesSurveyList.QuestionnaireListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_questionnaire_survey, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).tvTitle.setText(data.get(position).getQuestionnaireTitle());
            ((ViewHolder) holder).tvPublisher.setText(data.get(position).getPublisher());
            ((ViewHolder) holder).tvTime.setText(data.get(position).getReleaseTime());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, QuesSurveyDetailActivity.class);
                    intent.putExtra("questionnaireId", data.get(position).getQuestionnaireId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.tv_publisher)
        TextView tvPublisher;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
