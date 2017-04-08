package com.zidian.teacher.ui.course.activity;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;

/**
 * 考勤统计
 * Created by GongCheng on 2017/4/7.
 */

public class AttendanceStatisticsActivity extends BaseActivity {
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

    }
}
