package com.zidian.teacher.ui.course.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.ui.widget.CourseInfo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 课程信息
 * Created by GongCheng on 2017/4/7.
 */

public class CourseInfoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_course_name)
    TextView tvCourseName;
    @BindView(R.id.tv_course_location)
    TextView tvCourseLocation;
    @BindView(R.id.tv_course_week)
    TextView tvCourseWeek;

    private CourseInfo courseInfo;

    @Override
    protected int getLayout() {
        return R.layout.activity_course_info;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        courseInfo = (CourseInfo) getIntent().getSerializableExtra("courseInfo");
        toolbar.setTitle(getString(R.string.course_info));
        setToolbarBack(toolbar);
        tvCourseName.setText(getString(R.string.course_name, courseInfo.getClassname()));
        tvCourseLocation.setText(getString(R.string.course_location, courseInfo.getClassRoom()));
        tvCourseWeek.setText(getString(R.string.course_current_week, courseInfo.getBeginEndWeek()));
    }


    @OnClick({R.id.tv_attendance, R.id.tv_attendance_statistics})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_attendance:
                Intent intentAttendance = new Intent(this, AttendanceActivity.class);
                intentAttendance.putExtra("courseInfo", courseInfo);
                startActivity(intentAttendance);
                break;
            case R.id.tv_attendance_statistics:
                Intent intentAttendanceStatistics = new Intent(this, AttendanceStatisticsActivity.class);
                intentAttendanceStatistics.putExtra("courseInfo", courseInfo);
                startActivity(intentAttendanceStatistics);
                break;
        }
    }
}
