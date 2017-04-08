package com.zidian.teacher.ui.course.activity;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static dagger.internal.Preconditions.checkNotNull;

/**
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

    private List<Class> classes;
    private List<AttendanceStudent.DataBean> students;
    private CourseInfo courseInfo;
    private AttendanceAdapter adapter;
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
        adapter = new AttendanceAdapter(this, students);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerViewLinearDecoration(this, RecyclerViewLinearDecoration.VERTICAL_LIST));
        //点击事件改变选择状态
        adapter.setOnItemClickListener(new AttendanceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                for (int i = 0; i < students.size(); i++) {
                    if (i == position) {
                        students.get(i).setSelect(true);
                    } else {
                        students.get(i).setSelect(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        checkNotNull(presenter);
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

    }

    @Override
    public void showClasses(List<Class> classes) {
        this.classes = classes;
        spinner.setItems(classes);
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

    }

    @Override
    public void showSuccess() {

    }
}
