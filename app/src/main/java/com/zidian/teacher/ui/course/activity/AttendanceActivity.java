package com.zidian.teacher.ui.course.activity;

import android.app.ProgressDialog;
import android.support.annotation.StringDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.Class;
import com.zidian.teacher.presenter.AttendancePresenter;
import com.zidian.teacher.presenter.contract.AttendanceContract;
import com.zidian.teacher.ui.course.adapter.AttendanceAdapter;
import com.zidian.teacher.ui.widget.CourseInfo;
import com.zidian.teacher.ui.widget.RecyclerViewLinearDecoration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * 本节考勤
 * Created by GongCheng on 2017/4/7.
 */

public class AttendanceActivity extends BaseActivity implements AttendanceContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Inject
    AttendancePresenter presenter;
    @Inject
    AttendanceAdapter adapter;

    private List<Class> classes;
    private List<AttendanceStudent.DataBean> students;
    private CourseInfo courseInfo;
    private ProgressDialog progressDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_attendance;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);
        checkNotNull(adapter);
        classes = new ArrayList<>();
        students = new ArrayList<>();
        courseInfo = (CourseInfo) getIntent().getSerializableExtra("courseInfo");

        toolbar.setTitle(R.string.attendance);
        setToolbarBack(toolbar);
        errorView.setVisibility(View.GONE);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Class>() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Class cls) {
                presenter.getAttendanceStudents(courseInfo.getCourseWeeklyId(), courseInfo.getCourseId(),
                        classes.get(i).getClassId());
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.attendance_loading));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerViewLinearDecoration(this, RecyclerViewLinearDecoration.VERTICAL_LIST));

        presenter.attachView(this);
        presenter.getClasses(courseInfo.getCourseId());

    }

    @StringDef({
            AttendanceStatus.LATE, AttendanceStatus.LEAVE_EARLY,
            AttendanceStatus.ABSENTEEISM, AttendanceStatus.LEAVE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface AttendanceStatus {
        String LATE = "1";
        String LEAVE_EARLY = "2";
        String ABSENTEEISM = "3";
        String LEAVE = "4";
    }

    @OnClick({R.id.btn_late, R.id.btn_leave_early, R.id.btn_absenteeism, R.id.btn_leave, R.id.btn_submit})
    public void onAttendanceStatusClick(View view) {
        switch (view.getId()) {
            case R.id.btn_late:
                adapter.setStudentAttendance(AttendanceStatus.LATE);
                break;
            case R.id.btn_leave_early:
                adapter.setStudentAttendance(AttendanceStatus.LEAVE_EARLY);
                break;
            case R.id.btn_absenteeism:
                adapter.setStudentAttendance(AttendanceStatus.ABSENTEEISM);
                break;
            case R.id.btn_leave:
                adapter.setStudentAttendance(AttendanceStatus.LEAVE);
                break;
            case R.id.btn_submit:
                presenter.setAttendance(adapter.getStudentJson(), courseInfo.getCourseId(), courseInfo.getCourseWeeklyId());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        progressDialog.dismiss();
    }

    @Override
    public void showClasses(List<Class> classes) {
        this.classes = classes;
        spinner.setItems(classes);
        //获取班级列表成功后获取第一个班级的学生
        presenter.getAttendanceStudents(courseInfo.getCourseWeeklyId(), courseInfo.getCourseId(),
                classes.get(0).getClassId());
    }

    @Override
    public void showLoadingStudents() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadStudentsError(Throwable throwable) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        students.clear();
        adapter.setStudents(students);
        errorView.setText(throwable.getMessage());
    }

    @Override
    public void showStudents(List<AttendanceStudent.DataBean> students) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        adapter.setStudents(students);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, getString(R.string.attendance_succeed),Toast.LENGTH_SHORT).show();
        finish();
    }
}
