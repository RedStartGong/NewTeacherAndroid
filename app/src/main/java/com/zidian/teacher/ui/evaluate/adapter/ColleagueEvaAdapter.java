package com.zidian.teacher.ui.evaluate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.zidian.teacher.R;

import com.zidian.teacher.model.entity.remote.ColleagueEva;
import com.zidian.teacher.ui.widget.BarChartHelper;
import com.zidian.teacher.util.ColorConstants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GongCheng on 2017/1/9.
 */

public class ColleagueEvaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_TOP = 0;
    private final static int TYPE_ITEM = 1;

    private List<ColleagueEva> data;
    private BarChartHelper barChartHelper;

    @Inject
    public ColleagueEvaAdapter(BarChartHelper barChartHelper) {
        this.barChartHelper = barChartHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bar_chart, parent, false);
            return new TopViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evaluate_detail_chart, parent, false);
            return new ItemViewHolder(view);
        }
    }


    public interface OnItemClickListener {
        void onClick(String indexName, float indexScore);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            barChartHelper.initBarChart(((TopViewHolder) holder).colleagueBarChart);
            ((TopViewHolder) holder).colleagueBarChart.setData(getBarData());
            ((TopViewHolder) holder).colleagueBarChart.invalidate();
        } else if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).evaluateColor.setBackgroundColor(ColorConstants.CHART_COLORS[position - 1]);
            ((ItemViewHolder) holder).evaluateScored.setText(decimal(data.get(position - 1).getIndexScore()) + "");
            ((ItemViewHolder) holder).evaluateType.setText(data.get(position - 1).getIndexName());
            StringBuffer sb = new StringBuffer();

            if (data.get(position - 1).getTwoIndexList().size() == 1) {
                sb.append(data.get(position - 1).getTwoIndexList().get(0));
            } else {
                sb.append(data.get(position - 1).getTwoIndexList().get(0) + "、");
            }
            for (int i = 1; i < data.get(position - 1).getTwoIndexList().size(); i++) {
                if (i == data.get(position - 1).getTwoIndexList().size() - 1) {
                    sb.append(data.get(position - 1).getTwoIndexList().get(i));
                } else {
                    sb.append(data.get(position - 1).getTwoIndexList().get(i) + "、");
                }
            }
            ((ItemViewHolder) holder).evaluateTag.setText(sb.toString());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(data.get(holder.getAdapterPosition() - 1).getIndexName(),
                            decimal(data.get(holder.getAdapterPosition() - 1).getIndexScore()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * 得到BarChart的data
     *
     * @return BarData
     */
    private BarData getBarData() {
        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            barEntries.add(new BarEntry(i + 1, decimal(data.get(i).getIndexScore())));
        }
        BarDataSet dataSet = new BarDataSet(barEntries, "BarChart");
        dataSet.setColors(ColorConstants.CHART_COLORS);
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.3f);
        barData.setValueTextSize(12);
        return barData;
    }

    /**
     * 精确到小数点后2位
     *
     * @param f float number
     * @return  number
     */
    private float decimal(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        String format = decimalFormat.format(f);
        return Float.parseFloat(format);
    }

    public void setData(List<ColleagueEva> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.colleague_bar_chart)
        BarChart colleagueBarChart;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_evaluate_color)
        ImageView evaluateColor;
        @BindView(R.id.tv_evaluate_type)
        TextView evaluateType;
        @BindView(R.id.tv_evaluate_tag)
        TextView evaluateTag;
        @BindView(R.id.tv_evaluate_scored)
        TextView evaluateScored;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
