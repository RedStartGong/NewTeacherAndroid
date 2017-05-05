package com.zidian.teacher.ui.questionnaire.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.zidian.teacher.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/24 0024.
 * 问卷详情适配器
 */
public class QuesSurveyDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private int questionState = 0;
    private Context context;
    private List<Map<String, String>> data;
    private String[] list = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public QuesSurveyDetailAdapter(Context context, List<Map<String, String>> data) {
        this.context = context;
        this.data = data;
    }

    public interface OnChoseItemClickListener {
        void onItemClick(View view, int position);

    }

    private OnChoseItemClickListener onChoseItemClickListener;

    public void setOnItemClickListener(OnChoseItemClickListener onChoseItemClickListener) {
        this.onChoseItemClickListener = onChoseItemClickListener;
    }

    public interface OnStarItemClickListener {
        void onItemClick(float rating, int position, RatingBar ratingbar);

    }

    private OnStarItemClickListener onStarItemClickListener;

    public void setOnItemClickListener(OnStarItemClickListener onStarItemClickListener) {
        this.onStarItemClickListener = onStarItemClickListener;
    }


    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (questionState == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_ques_survey_score, parent,
                    false);
            return new StarViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_ques_choose, parent,
                    false);
            return new ItemViewHolder(view);
        }
    }

    public void setState(int state) {
        this.questionState = state;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StarViewHolder) {
            ((StarViewHolder) holder).tvOptionTitle.setText(data.get(position).get("optionsDescribe"));
            if (onStarItemClickListener != null) {


                ((StarViewHolder) holder).ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        int position = holder.getLayoutPosition();
                        onStarItemClickListener.onItemClick(rating, position, ((StarViewHolder) holder).ratingBar);
                    }
                });

            }
        } else if (holder instanceof ItemViewHolder) {

            ((ItemViewHolder) holder).tvOptionContent.setText(data.get(position).get("optionsDescribe"));
            ((ItemViewHolder) holder).tvTitle.setText(list[position]);
            if (data.get(position).get("result").equals(position + "")) {
                ((ItemViewHolder) holder).ivChoose.setVisibility(View.VISIBLE);
            } else {
                ((ItemViewHolder) holder).ivChoose.setVisibility(View.GONE);
            }

            if (onChoseItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onChoseItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
            }
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_option_Title)
        TextView tvTitle;
        @BindView(R.id.tv_option_content)
        TextView tvOptionContent;
        @BindView(R.id.iv_choose)
        ImageView ivChoose;


        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    static class StarViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_option_title)
        TextView tvOptionTitle;
        @BindView(R.id.rating_bar)
        RatingBar ratingBar;

        public StarViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, itemView);
        }
    }

}
