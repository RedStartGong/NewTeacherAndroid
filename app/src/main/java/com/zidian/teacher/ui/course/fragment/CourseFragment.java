package com.zidian.teacher.ui.course.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseFragment;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.model.entity.remote.CourseTime;
import com.zidian.teacher.presenter.SchedulePresenter;
import com.zidian.teacher.presenter.contract.ScheduleContract;
import com.zidian.teacher.ui.course.activity.CourseInfoActivity;
import com.zidian.teacher.ui.widget.CourseInfo;
import com.zidian.teacher.ui.widget.ScheduleView;
import com.zidian.teacher.util.SharedPreferencesUtils;
import com.zidian.teacher.util.SnackbarUtils;
import com.zidian.teacher.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static dagger.internal.Preconditions.checkNotNull;


/**
 * Created by GongCheng on 2017/3/15.
 */

public class CourseFragment extends BaseFragment implements ScheduleContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.schedule_view)
    ScheduleView scheduleView;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Inject
    SchedulePresenter presenter;

    private int currentWeek;
    private List<String> dateList;
    private List<CourseInfo> courseInfoList;

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
        dateList = new ArrayList<>();
        courseInfoList = new ArrayList<>();
        toolbar.setTitle(R.string.main_course);
        errorView.setVisibility(View.GONE);
        spinner.setItems(getWeeks());
        //设置当前周
        currentWeek = SharedPreferencesUtils.getCurrentWeek() == 0 ? 1 : SharedPreferencesUtils.getCurrentWeek();
        spinner.setSelectedIndex(currentWeek - 1);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, String item) {
                currentWeek = i + 1;
                SharedPreferencesUtils.setCurrentWeek(currentWeek);
                presenter.getCourseTime(currentWeek);
            }
        });
        scheduleView.setOnItemClassClickListener(new ScheduleView.OnItemClassClickListener() {
            @Override
            public void onClick(CourseInfo courseInfo) {
                Intent intent = new Intent(activity, CourseInfoActivity.class);
                intent.putExtra("courseInfo", courseInfo);
                startActivity(intent);
            }
        });
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getCourseTime(currentWeek);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.deAttachView();
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


    @Override
    public void showError(Throwable e) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(e.getMessage());
        scheduleView.setData(null, null);
    }

    @Override
    public void showSchedule(List<Course> courses) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        for (int i = 0; i < courses.size(); i++) {
            CourseInfo courseInfo = new CourseInfo();
            courseInfo.setClassname(courses.get(i).getCourseName());
            courseInfo.setFromClassNum(courses.get(i).getClassBegin());
            courseInfo.setClassNumLen(courses.get(i).getClassEnd() - courses.get(i).getClassBegin() + 1);
            courseInfo.setClassRoom(courses.get(i).getClassroom());
            courseInfo.setWeekday(courses.get(i).getWeekDay());
            courseInfo.setCourseId(String.valueOf(courses.get(i).getCoursePlanId()));
            courseInfo.setBeginEndWeek(String.valueOf(currentWeek));
            courseInfoList.add(courseInfo);
        }
        scheduleView.setData(courseInfoList, dateList);
    }

    @Override
    public void showLoading() {
        scheduleView.setData(null, null);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCourseTime(CourseTime courseTime) {
        dateList.clear();
        for (int i = 0; i < courseTime.getDayTime().size(); i++) {
            dateList.add(TimeUtils.millis2String(courseTime.getDayTime().get(i).getTime(), "MM/dd"));
        }
        presenter.getSchedule(currentWeek);
    }
}



