package com.zidian.teacher.ui.evaluate.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.StudentEva;
import com.zidian.teacher.ui.evaluate.activity.StudentEvaTwoIndexActivity;
import com.zidian.teacher.util.ColorConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 学生评价adapter
 * Created by GongCheng on 2017/1/9.
 */

public class StudentEvaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_TOP = 0;
    private final static int TYPE_ITEM = 1;
    private StudentEva studentEva;

    @Inject
    public StudentEvaAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_eva_top, parent, false);
            return new TopViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_eva, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TopViewHolder) {
            ((TopViewHolder) holder).myAvgScore.setText(String.valueOf(studentEva.getMyAvg()));
            ((TopViewHolder) holder).schoolAvgScore.setText(String.valueOf(studentEva.getSchoolScore()));
            ((TopViewHolder) holder).majorAvgScore.setText(String.valueOf(studentEva.getCollegeScore()));
            ((TopViewHolder) holder).rank.setText(String.valueOf(studentEva.getTeacherRank()));
        } else if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).evaluateColor.setBackgroundColor(ColorConstants.CHART_COLORS[position - 1]);
            ((ItemViewHolder) holder).evaluateType.setText(studentEva.getIndexOneMapList().get(position - 1).getIndexOneName());
            StringBuilder sb = new StringBuilder();
            //二级指标列表
            List<StudentEva.IndexOneMapListBean.IndexTwoListBean> twoIndexList =
                    studentEva.getIndexOneMapList().get(position - 1).getIndexTwoList();
            if (twoIndexList.size() == 1) {
                sb.append(twoIndexList.get(0).getIndexTwoName());
            } else {
                sb.append(twoIndexList.get(0).getIndexTwoName()).append("、");
            }
            for (int i = 1; i < twoIndexList.size(); i++) {
                //最后一个拼接
                if (i == twoIndexList.size() - 1) {
                    sb.append(twoIndexList.get(i).getIndexTwoName());
                } else {
                    sb.append(twoIndexList.get(i).getIndexTwoName()).append("、");
                }
            }
            ((ItemViewHolder) holder).evaluateTag.setText(sb.toString());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), StudentEvaTwoIndexActivity.class);
                    intent.putExtra("oneIndexId", studentEva.getIndexOneMapList().get(position - 1).getIndexOneId());
                    intent.putExtra("oneIndexName", studentEva.getIndexOneMapList().get(position - 1).getIndexOneName());
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return studentEva == null ? 0 : studentEva.getIndexOneMapList().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setData(StudentEva studentEva) {
        this.studentEva = studentEva;
        notifyDataSetChanged();
    }

    static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_my_avg_score)
        TextView myAvgScore;
        @BindView(R.id.tv_school_avg_score)
        TextView schoolAvgScore;
        @BindView(R.id.tv_major_avg_score)
        TextView majorAvgScore;
        @BindView(R.id.tv_rank)
        TextView rank;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_evaluate_color)
        ImageView evaluateColor;
        @BindView(R.id.tv_one_index_name)
        TextView evaluateType;
        @BindView(R.id.tv_evaluate_tag)
        TextView evaluateTag;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
