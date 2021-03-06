package com.zidian.teacher.ui.evaluate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zidian.teacher.R;

import com.zidian.teacher.model.entity.remote.EvaTwoIndex;
import com.zidian.teacher.util.ColorConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图表描述Adapter
 * Created by GongCheng on 2016/11/15.
 */

public class ChartOptionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<EvaTwoIndex> data;

    @Inject
    public ChartOptionListAdapter() {

    }

    public void setData(List<EvaTwoIndex> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two_index_tag, parent, false);

        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OptionViewHolder) {
            ((OptionViewHolder) holder).tvOptionDescribe.setText(data.get(position).getTwoIndexName());
            ((OptionViewHolder) holder).tvOptionColor.setBackgroundColor(ColorConstants.CHART_COLORS[position]);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class OptionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_option_color)
        ImageView tvOptionColor;
        @BindView(R.id.tv_option_describe)
        TextView tvOptionDescribe;

        public OptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
