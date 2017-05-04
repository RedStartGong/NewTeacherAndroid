package com.zidian.teacher.ui.questionnaire.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.MyQuesDetail;
import com.zidian.teacher.util.ColorConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图标描述Adapter
 * Created by GongCheng on 2016/11/15.
 */

public class ChartOptionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyQuesDetail.StatisticalBean> describes;

    public void setData(List<MyQuesDetail.StatisticalBean> describes) {
        this.describes = describes;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_questionnare_option_describe, parent, false);

        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OptionViewHolder) {
            ((OptionViewHolder) holder).optionDescribeTv.setText(describes.get(position).getOptionsDescribe());
            ((OptionViewHolder) holder).optionColorIv.setBackgroundColor(ColorConstants.CHART_COLORS[position]);
            ((OptionViewHolder) holder).tvOptionCount.setText(
                    holder.itemView.getContext().getString(R.string.option_count,describes.get(position).getStisNum()));
        }
    }

    @Override
    public int getItemCount() {
        return describes == null ? 0 : describes.size();
    }

    class OptionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_option_color)
        ImageView optionColorIv;
        @BindView(R.id.tv_option_describe)
        TextView optionDescribeTv;
        @BindView(R.id.tv_option_count)
        TextView tvOptionCount;


        OptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
