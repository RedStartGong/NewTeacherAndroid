package com.zidian.teacher.ui.widget;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import javax.inject.Inject;

/**
 * 条形图帮助类
 * Created by GongCheng on 2017/4/28.
 */

public class BarChartHelper {
    @Inject
    public BarChartHelper() {

    }
    /**
     * 初始化条形图
     *
     * @param chart
     */
    public void initBarChart(BarChart chart) {
        chart.setDescription(null);
        //设置拦截所有触摸事件
        chart.setTouchEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(5);
        xAxis.setTextSize(10f);
        //X轴Value设计
//        xAxis.setValueFormatter(valueFormatter);
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "";
            }

        });
        //Y轴左侧
        YAxis yl = chart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(false);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        //Y轴右侧
        YAxis yr = chart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        chart.animateY(1500);
        //图表说明设置
        Legend l = chart.getLegend();
        l.setXEntrySpace(7f);
        l.setYOffset(0f);
        l.setEnabled(false);
    }

}
