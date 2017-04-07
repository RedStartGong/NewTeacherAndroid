package com.zidian.teacher.ui.course.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.presenter.CoursePresenter;
import com.zidian.teacher.presenter.contract.CourseContract;
import com.zidian.teacher.ui.widget.ClassInfo;
import com.zidian.teacher.ui.widget.ScheduleView;
import com.zidian.teacher.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/3/15.
 */

public class CourseFragment extends BaseFragment implements CourseContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.schedule_view)
    ScheduleView scheduleView;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    @Inject
    CoursePresenter presenter;

    private List<ClassInfo> classInfos;
    private List<Course> courses;
    private int currentWeek;

    public static CourseFragment newInstance() {

        Bundle args = new Bundle();

        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        classInfos = new ArrayList<>();
        courses = new ArrayList<>();
        toolbar.setTitle(R.string.main_course);
        spinner.setItems(getWeeks());
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, String item) {
                currentWeek = i + 1;
                setScheduleView();
            }
        });
        scheduleView.setOnItemClassClickListener(new ScheduleView.OnItemClassClickListener() {
            @Override
            public void onClick(ClassInfo classInfo) {

            }
        });
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getCourse();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.deAttachView();
    }

    @Override
    public void showError(Throwable e) {
        SnackbarUtils.showShort(scheduleView, e.getMessage());
    }

    @Override
    public void showCourse(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * 设置课表
     */
    private void setScheduleView() {
        if (courses.isEmpty()) {
            presenter.getCourse();
        } else {
            classInfos.clear();
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getBeginEndWeek().equals(String.valueOf(currentWeek))) {
                    ClassInfo classInfo = new ClassInfo();
                    classInfo.setClassname(courses.get(i).getCourseName());
                    classInfo.setFromClassNum(getBeginClass(courses.get(i).getWeeklyQuarter()));
                    classInfo.setClassNumLen(getClassLength(courses.get(i).getWeeklyQuarter()));
                    classInfo.setClassRoom(courses.get(i).getClassRoom());
                    classInfo.setWeekday(getWeekDay(courses.get(i).getWeeklyDay()));
                    classInfo.setBeginEndWeek(courses.get(i).getBeginEndWeek());
                    classInfo.setCourseId(courses.get(i).getCourseId());
                    classInfo.setCourseWeeklyId(courses.get(i).getCourseWeeklyId());

                    classInfos.add(classInfo);
                }
            }
            scheduleView.setClassList(classInfos);
        }
    }
    /**
     * 设置周次
     *
     * @return weeks
     */
    private List<String> getWeeks() {
        List<String> weeks = new ArrayList<>();
        for (int i = 1; i < 24; i++) {
            weeks.add(getString(R.string.course_week, i));
        }
        return weeks;
    }
    private int getBeginClass(String myClass) {

        if (myClass.indexOf(",") == -1) {
            return Integer.parseInt(myClass);
        } else {
            String b = myClass.substring(0, myClass.indexOf(","));
            int i = Integer.parseInt(b);
            return i;
        }
    }

    private int getClassLength(String myClass) {
        if (myClass.lastIndexOf(",") != -1) {
            String c = myClass.substring(myClass.lastIndexOf(",") + 1, myClass.length());
            String b = myClass.substring(0, myClass.indexOf(","));
            int i = Integer.parseInt(b);
            int j = Integer.parseInt(c);
            return j - i + 1;
        } else {
            return 1;
        }

    }
    private int getWeekDay(String weeklyDay) {

        int day = 1;

        if (weeklyDay.equals("周一")) {
            day = 1;
        } else if (weeklyDay.equals("周二")) {
            day = 2;
        } else if (weeklyDay.equals("周三")) {
            day = 3;
        } else if (weeklyDay.equals("周四")) {
            day = 4;
        } else if (weeklyDay.equals("周五")) {
            day = 5;
        } else if (weeklyDay.equals("周六")) {
            day = 6;
        } else if (weeklyDay.equals("周日")) {
            day = 7;
        }
        return day;

    }
}



