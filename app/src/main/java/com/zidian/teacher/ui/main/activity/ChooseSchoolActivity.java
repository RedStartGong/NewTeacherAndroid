package com.zidian.teacher.ui.main.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.School;
import com.zidian.teacher.presenter.ChooseSchoolPresenter;
import com.zidian.teacher.presenter.contract.ChooseSchoolContract;
import com.zidian.teacher.ui.main.adapter.ProvinceAdapter;
import com.zidian.teacher.ui.main.adapter.SchoolsAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/8/18.
 */

public class ChooseSchoolActivity extends BaseActivity implements ChooseSchoolContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_province)
    RecyclerView rvProvince;
    @BindView(R.id.rv_school)
    RecyclerView rvSchool;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Inject
    ChooseSchoolPresenter presenter;
    @Inject
    ProvinceAdapter provinceAdapter;
    @Inject
    SchoolsAdapter schoolsAdapter;

    private School.SchoolBean schoolBean;
    private List<School> schools;
    private int provincePosition = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_choose_school;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        schools = Collections.emptyList();
        errorView.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        setToolbarBack(toolbar);
        getSupportActionBar().setTitle("选择学校");
        rvProvince.setLayoutManager(new LinearLayoutManager(this));
        rvProvince.setAdapter(provinceAdapter);
        rvSchool.setLayoutManager(new LinearLayoutManager(this));
        rvSchool.setAdapter(schoolsAdapter);
        //省列表点击事件
        provinceAdapter.setOnItemClickListener(new ProvinceAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                schoolsAdapter.setData(schools.get(position).getSchool());
                provincePosition = position;
            }
        });
        //学校列表点击事件
        schoolsAdapter.setOnItemClickListener(new SchoolsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                schoolBean = schools.get(provincePosition).getSchool().get(position);
                Intent intent = new Intent();
                intent.putExtra("school", schoolBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getSchools();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
    }

    @Override
    public void showSchools(List<School> schools) {
        this.schools = schools;
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        provinceAdapter.setData(schools);
        schoolsAdapter.setData(schools.get(0).getSchool());
    }
}
