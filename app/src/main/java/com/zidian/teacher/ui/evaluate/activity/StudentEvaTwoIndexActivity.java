package com.zidian.teacher.ui.evaluate.activity;

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
import com.zidian.teacher.model.entity.remote.EvaTwoIndex;
import com.zidian.teacher.presenter.StudentEvaTwoIndexPresenter;
import com.zidian.teacher.presenter.contract.StudentEvaTwoIndexContract;
import com.zidian.teacher.ui.evaluate.adapter.ChartOptionListAdapter;
import com.zidian.teacher.ui.evaluate.chart.BarChartHelper;
import com.zidian.teacher.util.ColorConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/4/28.
 */

public class StudentEvaTwoIndexActivity extends BaseActivity implements StudentEvaTwoIndexContract.View{
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.bar_chart_two_index)
    BarChart barChartTwoIndex;
    @BindView(R.id.rv_eva_tag)
    RecyclerView rvEvaTag;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    StudentEvaTwoIndexPresenter presenter;
    @Inject
    ChartOptionListAdapter adapter;
    @Inject
    BarChartHelper barChartHelper;

    @Override
    protected int getLayout() {
        return R.layout.activity_student_eva_two_indext;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        String indexName = getIntent().getStringExtra("indexName");
        toolbar.setTitle(indexName);
        setToolbarBack(toolbar);
        errorView.setVisibility(View.GONE);
        rvEvaTag.setLayoutManager(new LinearLayoutManager(this));
        rvEvaTag.setAdapter(adapter);
        barChartHelper.initBarChart(barChartTwoIndex);
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getStudentEvaTwoIndex(indexName);
    }

    /**
     * 得到BarChart的data
     *
     * @return
     */
    public BarData getBarData(List<EvaTwoIndex> evaTwoIndices) {
        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < evaTwoIndices.size(); i++) {
            barEntries.add(new BarEntry(i + 1, evaTwoIndices.get(i).getTwoIndexScore()));
        }
        BarDataSet dataSet = new BarDataSet(barEntries, "BarChart");
        dataSet.setColors(ColorConstants.CHART_COLORS);
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.3f);
        barData.setValueTextSize(12);
        return barData;
    }

    @Override
    public void showError(Throwable e) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showEvaTwoIndex(List<EvaTwoIndex> evaTwoIndexes) {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        barChartTwoIndex.setData(getBarData(evaTwoIndexes));
        adapter.setData(evaTwoIndexes);
    }
}
