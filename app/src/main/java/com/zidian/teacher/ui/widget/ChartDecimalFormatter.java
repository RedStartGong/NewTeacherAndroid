package com.zidian.teacher.ui.widget;


import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * chart value formatter
 * Created by GongCheng on 2017/6/6.
 */

public class ChartDecimalFormatter implements IValueFormatter {
    private DecimalFormat decimalFormat;

    public ChartDecimalFormatter(String format) {
        decimalFormat = new DecimalFormat(format);
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return decimalFormat.format(value);
    }
}
