package com.zidian.teacher.ui.evaluate.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.ColleagueEva;
import com.zidian.teacher.model.entity.remote.EvaTwoIndex;
import com.zidian.teacher.presenter.ColleagueEvaTwoIndexPresenter;
import com.zidian.teacher.presenter.contract.ColleagueEvaTwoIndexContract;
import com.zidian.teacher.ui.evaluate.adapter.ChartOptionListAdapter;
import com.zidian.teacher.ui.widget.BarChartHelper;
import com.zidian.teacher.ui.widget.ChartDecimalFormatter;
import com.zidian.teacher.util.ColorConstants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * 同行/督导评价二级指标
 * Created by GongCheng on 2017/4/28.
 */

public class ColleagueEvaTwoIndexActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_index_score)
    TextView tvIndexScore;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.rv_eva_tag)
    RecyclerView rvEvaTag;
    @BindView(R.id.bar_chart_two_index)
    BarChart barChartTwoIndex;

    @Inject
    ColleagueEvaTwoIndexPresenter presenter;
    @Inject
    ChartOptionListAdapter adapter;
    @Inject
    BarChartHelper barChartHelper;

    private List<ColleagueEva.TwoIndexListBean> twoIndexListBeanList;

    @Override
    protected int getLayout() {
        return R.layout.activity_colleague_eva_two_index;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);
        checkNotNull(adapter);
        checkNotNull(barChartHelper);
        Intent intent = getIntent();
        twoIndexListBeanList = intent.getParcelableArrayListExtra("twoIndex");
        String oneIndexName = intent.getStringExtra("oneIndexName");
        float oneIndexScore = intent.getFloatExtra("oneIndexScore", 0);
        toolbar.setTitle(oneIndexName);
        setToolbarBack(toolbar);
        //格式化数字，保留小数点后两位
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        tvIndexScore.setText(decimalFormat.format(oneIndexScore));
        errorView.setVisibility(View.GONE);
        rvEvaTag.setLayoutManager(new LinearLayoutManager(this));
        rvEvaTag.setAdapter(adapter);
        barChartHelper.initBarChart(barChartTwoIndex);
        //设置数据
        adapter.setData(getEvaTwoIndeces());
        barChartTwoIndex.setData(getBarData(getEvaTwoIndeces()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    /**
     * 得到BarChart的data
     *
     * @return BarData
     */
    public BarData getBarData(List<EvaTwoIndex> evaTwoIndices) {
        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < evaTwoIndices.size(); i++) {
            barEntries.add(new BarEntry(i + 1, evaTwoIndices.get(i).getTwoIndexScore()));
        }
        BarDataSet dataSet = new BarDataSet(barEntries, "BarChart");
        dataSet.setColors(ColorConstants.CHART_COLORS);
        BarData barData = new BarData(dataSet);
        barData.setValueFormatter(new ChartDecimalFormatter("##0.00"));
        barData.setBarWidth(0.3f);
        barData.setValueTextSize(12);
        return barData;
    }

    /**
     * 转换二级标签list
     *
     * @return {@link EvaTwoIndex list}
     */
    public List<EvaTwoIndex> getEvaTwoIndeces() {
        List<EvaTwoIndex> evaTwoIndices = new ArrayList<>();
        EvaTwoIndex evaTwoIndex = new EvaTwoIndex();
        for (int i = 0; i < twoIndexListBeanList.size(); i++) {
            evaTwoIndex.setTwoIndexName(twoIndexListBeanList.get(i).getTwoIndexName());
            evaTwoIndex.setTwoIndexId(twoIndexListBeanList.get(i).getTwoIndexId());
            evaTwoIndex.setTwoIndexScore(twoIndexListBeanList.get(i).getTwoIndexScore());
            evaTwoIndices.add(evaTwoIndex);
        }
        return evaTwoIndices;
    }

}
