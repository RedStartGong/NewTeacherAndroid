package com.zidian.teacher.ui.course.activity;

import android.support.v7.widget.Toolbar;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.Class;
import com.zidian.teacher.presenter.AttendancePresenter;
import com.zidian.teacher.presenter.contract.AttendanceContract;
import com.zidian.teacher.ui.widget.ClassInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/4/7.
 */

public class AttendanceActivity extends BaseActivity implements AttendanceContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    @Inject
    AttendancePresenter presenter;

    private List<Class> classes;

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
        toolbar.setTitle(R.string.attendance);
        classes = new ArrayList<>();
        setToolbarBack(toolbar);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Class>() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Class cls) {

            }
        });
        ClassInfo classInfo = (ClassInfo) getIntent().getSerializableExtra("classInfo");
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getClasses(classInfo.getCourseId());
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
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

    }
}
