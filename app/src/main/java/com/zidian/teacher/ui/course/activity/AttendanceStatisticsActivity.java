package com.zidian.teacher.ui.course.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.StudentClass;
import com.zidian.teacher.presenter.AttendanceStatisticsPresenter;
import com.zidian.teacher.presenter.contract.AttendanceStatisticsContract;
import com.zidian.teacher.ui.course.adapter.AttendanceStatisticsAdapter;
import com.zidian.teacher.ui.widget.CourseInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zidian.teacher.util.Preconditions.checkNotNull;


/**
 * 考勤统计
 * Created by GongCheng on 2017/4/7.
 */

public class AttendanceStatisticsActivity extends BaseActivity implements AttendanceStatisticsContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    AttendanceStatisticsPresenter presenter;
    @Inject
    AttendanceStatisticsAdapter adapter;

    private CourseInfo courseInfo;
    private List<AttendanceStatistics> attendanceStatistics;

    @Override
    protected int getLayout() {
        return R.layout.activity_attendance_statistics;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);
        checkNotNull(adapter);

        attendanceStatistics = new ArrayList<>();
        courseInfo = (CourseInfo) getIntent().getSerializableExtra("courseInfo");
        toolbar.setTitle(getString(R.string.attendance_statistics));
        setToolbarBack(toolbar);
        errorView.setVisibility(View.GONE);

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<StudentClass>() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, StudentClass cls) {
                presenter.getAttendanceStatistics(courseInfo.getCourseId(), cls.getClassName());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        presenter.attachView(this);
        presenter.getClasses(courseInfo.getCourseId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        loadingView.setVisibility(View.GONE);
        adapter.setAttendanceStatistics(Collections.<AttendanceStatistics>emptyList());
    }

    @Override
    public void showClasses(List<StudentClass> classes) {
        spinner.setItems(classes);
        //获取班级列表成功后获取第一个班级的考勤统计数据
        presenter.getAttendanceStatistics(courseInfo.getCourseId(), classes.get(0).getClassName());
    }

    @Override
    public void showLoading() {
        attendanceStatistics.clear();
        adapter.setAttendanceStatistics(attendanceStatistics);
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess() {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showStatistics(List<AttendanceStatistics> attendanceStatistics) {
        this.attendanceStatistics = attendanceStatistics;
        adapter.setAttendanceStatistics(this.attendanceStatistics);
    }
}
