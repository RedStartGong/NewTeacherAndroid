package com.zidian.teacher.ui.questionnaire.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.zidian.teacher.R;
import com.zidian.teacher.di.ActivityContext;
import com.zidian.teacher.model.entity.remote.MyQuesDetail;
import com.zidian.teacher.ui.widget.PieChartHelper;
import com.zidian.teacher.util.ColorConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的问卷详情 adapter
 * Created by GongCheng on 2017/5/4.
 */

public class MyQuesDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PieChartHelper pieChartHelper;
    private Context context;
    private List<MyQuesDetail> data;

    @Inject
    public MyQuesDetailAdapter(@ActivityContext Context context, PieChartHelper pieChartHelper) {
        this.pieChartHelper = pieChartHelper;
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<MyQuesDetail> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ques_detail_pie_chart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).tvQuestionnaireTitle
                    .setText(context.getString(R.string.my_ques_num,
                            position + 1,
                            data.get(position).getQuestionnaireItemName()));
            pieChartHelper.initPieChart(((ViewHolder) holder).pcQuestionnaireChart);
            ((ViewHolder) holder).pcQuestionnaireChart.setData(getPieData(position));
            ((ViewHolder) holder).pcQuestionnaireChart.invalidate();
            List<MyQuesDetail.QuestionnaireSelectionListBean> optionList =
                    data.get(position).getQuestionnaireSelectionList();
            Collections.sort(optionList, new Comparator<MyQuesDetail.QuestionnaireSelectionListBean>() {
                @Override
                public int compare(MyQuesDetail.QuestionnaireSelectionListBean o1, MyQuesDetail.QuestionnaireSelectionListBean o2) {
                    return o1.getSelectionId() - o2.getSelectionId();
                }
            });
            ((ViewHolder) holder).adapter.setData(optionList);
        }
    }

    /**
     * 得到饼状图数据
     *
     * @param position 位置
     * @return PieData
     */
    private PieData getPieData(int position) {
        List<PieEntry> pieEntries = new ArrayList<>();
        List<MyQuesDetail.QuestionnaireSelectionListBean> statisticalBeanList =
                data.get(position).getQuestionnaireSelectionList();

        for (int i = 0; i < statisticalBeanList.size(); i++) {
            if (statisticalBeanList.get(i).getSelectionNum() != 0) {
                pieEntries.add(new PieEntry(statisticalBeanList.get(i).getSelectionNum(),
                        statisticalBeanList.get(i).getSelectionName()));
            }
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, data.get(position).getQuestionnaireItemName());
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.parseColor("#4e342e"));
        dataSet.setColors(ColorConstants.CHART_COLORS);

        return new PieData(dataSet);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_questionnaire_title)
        TextView tvQuestionnaireTitle;
        @BindView(R.id.pc_questionnaire_chart)
        PieChart pcQuestionnaireChart;
        @BindView(R.id.rv_option_list)
        RecyclerView rvOptionList;

        private ChartOptionListAdapter adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            adapter = new ChartOptionListAdapter();
            rvOptionList.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rvOptionList.setAdapter(adapter);
        }
    }
}
