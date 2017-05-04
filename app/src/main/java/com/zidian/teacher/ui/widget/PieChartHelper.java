package com.zidian.teacher.ui.widget;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;

import javax.inject.Inject;

/**
 * 饼状图帮助类
 * Created by GongCheng on 2017/5/4.
 */

public class PieChartHelper {
    @Inject
    public PieChartHelper() {

    }

    public void initPieChart(PieChart chart) {
        chart.setDrawEntryLabels(true);
        //自动计算数据百分比
        chart.setUsePercentValues(true);
        chart.setDescription(null);
        chart.setTouchEnabled(false);
        chart.setEntryLabelTextSize(10f);
        chart.animateXY(1500, 1500);
        chart.setDrawEntryLabels(false);
        chart.setExtraOffsets(30.f, 0.f, 30.f, 0.f);
        chart.setHoleRadius(50f);
        chart.setTransparentCircleRadius(51f);
        //取消图表说明设置
        Legend l = chart.getLegend();
        l.setEnabled(false);
    }
}
