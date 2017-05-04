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
import com.zidian.teacher.model.entity.remote.MyQuesList;
import com.zidian.teacher.ui.questionnaire.activity.MyQuesDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class MyQuesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyQuesList.ListBean> data;
    private Context context;

    @Inject
    public MyQuesListAdapter(@ActivityContext Context context) {
        data = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<MyQuesList.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_questionnaire, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).tvTitle.setText(data.get(position).getQuestionnaireTitle());
            ((ViewHolder) holder).tvTime.setText(data.get(position).getReleaseTime());
            ((ViewHolder) holder).tvComplete.setText(
                    context.getString(R.string.my_questionnaire_complete,
                            data.get(position).getChooseNumber(),
                            data.get(position).getChooseNumber() + data.get(position).getNotChooseNumber()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MyQuesDetailActivity.class);
                    intent.putExtra("questionnaireId", data.get(position).getQuestionnaireId());
                    intent.putExtra("questionnaireTitle", data.get(position).getQuestionnaireTitle());
                    intent.putExtra("completeCount", data.get(position).getChooseNumber());
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
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_complete)
        TextView tvComplete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
