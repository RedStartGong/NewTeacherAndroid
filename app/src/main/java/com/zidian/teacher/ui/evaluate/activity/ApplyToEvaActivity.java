package com.zidian.teacher.ui.evaluate.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.EvaluateCourse;
import com.zidian.teacher.presenter.ApplyToEvaPresenter;
import com.zidian.teacher.presenter.contract.ApplyToEvaContract;
import com.zidian.teacher.util.SharedPreferencesUtils;
import com.zidian.teacher.util.SnackbarUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/4/13.
 */

public class ApplyToEvaActivity extends BaseActivity implements ApplyToEvaContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DataManager dataManager;
    @Inject
    ApplyToEvaPresenter presenter;
    @BindView(R.id.tv_college)
    TextView tvCollege;
    @BindView(R.id.tv_teacher_name)
    TextView tvTeacherName;
    @BindView(R.id.tv_course)
    TextView tvCourse;
    @BindView(R.id.tv_teaching_date)
    TextView tvTeachingDate;
    @BindView(R.id.tv_classroom)
    TextView tvClassroom;
    @BindView(R.id.til_apply_language)
    TextInputLayout tilApplyLanguage;
    @BindView(R.id.et_apply_language)
    EditText etApplyLanguage;

    private ProgressDialog progressDialog;
    private List<EvaluateCourse> courses;

    @Override
    protected int getLayout() {
        return R.layout.activity_apply_to_eva;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        courses = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中...");
        toolbar.setTitle("同行评价-评价他人");
        tilApplyLanguage.setCounterEnabled(true);
        tilApplyLanguage.setCounterMaxLength(20);
        setToolbarBack(toolbar);
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getEvaluateCourses();
        test();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @OnClick({R.id.ll_choose_college, R.id.ll_choose_teacher, R.id.ll_choose_course,
            R.id.ll_choose_teaching_date, R.id.ll_input_classroom, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_choose_college:
                chooseCollege();
                break;
            case R.id.ll_choose_teacher:
                chooseTeacher();
                break;
            case R.id.ll_choose_course:
                chooseCourse();
                break;
            case R.id.ll_choose_teaching_date:
                chooseTeachingDate();
                break;
            case R.id.ll_input_classroom:
                inputClassroom();
                break;
            case R.id.btn_confirm:
                confirm();
                break;
        }
    }

    private List<String> colleges;
    private List<String> teachers;
    private List<String> stringCourses;
    private List<String> stringCalendars;
    private String requestedPerson;
    private String teacherCollege;
    private String courseId;
    private String courseName;
    private String teachingCalendar;

    /**
     * 选择学院
     */
    private void chooseCollege() {
        if (colleges == null) {
            colleges = new ArrayList<>();
        }
        if (colleges.isEmpty()) {
            for (int i = 0; i < courses.size(); i++) {
                colleges.add(courses.get(i).getCollegeName());
            }
        }
        new MaterialDialog.Builder(this)
                .title("选择学院")
                .items(colleges)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence college) {
                        tvCollege.setText(college);
                        tvTeacherName.setText("");
                        tvCourse.setText("");
                        tvTeachingDate.setText("");
                        teacherCollege = college.toString();
                    }
                }).show();
    }

    /**
     * 选择教师
     */
    private void chooseTeacher() {
        if (TextUtils.isEmpty(tvCollege.getText())) {
            SnackbarUtils.showShort(toolbar, "请选择学院");
            return;
        }
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        final List<EvaluateCourse.CollegeOtherInformationBean> infoList =
                courses.get(colleges.indexOf(tvCollege.getText().toString())).getCollegeOtherInformation();
        teachers.clear();
        for (int i = 0; i < infoList.size(); i++) {
            teachers.add(infoList.get(i).getTeacherName());
        }
        new MaterialDialog.Builder(this)
                .title("选择教师")
                .items(teachers)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence teacherName) {
                        tvTeacherName.setText(teacherName);
                        tvCourse.setText("");
                        tvTeachingDate.setText("");
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("theRequestedPersonName", teacherName.toString());
                            jsonObject.put("theRequestedPersonId",
                                    infoList.get(teachers.indexOf(teacherName.toString())).getTeacherId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        requestedPerson = jsonObject.toString();
                    }
                }).show();
    }

    /**
     * 选择课程
     */
    private void chooseCourse() {
        if (TextUtils.isEmpty(tvCollege.getText())) {
            SnackbarUtils.showShort(toolbar, "请选择学院");
            return;
        }
        if (TextUtils.isEmpty(tvTeacherName.getText())) {
            SnackbarUtils.showShort(toolbar, "请选择教师");
            return;
        }
        if (stringCourses == null) {
            stringCourses = new ArrayList<>();
        }
        stringCourses.clear();
        final List<EvaluateCourse.CollegeOtherInformationBean.CourseBean> courseBeanList =
                courses.get(colleges.indexOf(tvCollege.getText().toString()))
                        .getCollegeOtherInformation()
                        .get(teachers.indexOf(tvTeacherName.getText().toString())).getCourse();
        for (int i = 0; i < courseBeanList.size(); i++) {
            stringCourses.add(courseBeanList.get(i).getCourseName());
        }
        new MaterialDialog.Builder(this)
                .title("选择课程")
                .items(stringCourses)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view,
                                            int i, CharSequence course) {
                        tvCourse.setText(course);
                        tvTeachingDate.setText("");
                        courseName = course.toString();
                        courseId = courseBeanList.get(stringCourses.indexOf(course.toString())).getCourseId();
                    }
                })
                .show();
    }

    /**
     * 选择教学日历
     */
    private void chooseTeachingDate() {
        if (TextUtils.isEmpty(tvCollege.getText())) {
            SnackbarUtils.showShort(toolbar, "请选择学院");
            return;
        }
        if (TextUtils.isEmpty(tvTeacherName.getText())) {
            SnackbarUtils.showShort(toolbar, "请选择教师");
            return;
        }
        if (TextUtils.isEmpty(tvCourse.getText())) {
            SnackbarUtils.showShort(toolbar, "请选择课程");
            return;
        }
        if (stringCalendars == null) {
            stringCalendars = new ArrayList<>();
        }
        stringCalendars.clear();
        List<EvaluateCourse.CollegeOtherInformationBean.CourseBean.TeachingCalendarTimeBean> calendars =
                courses.get(colleges.indexOf(tvCollege.getText().toString()))
                        .getCollegeOtherInformation().get(teachers.indexOf(tvTeacherName.getText().toString()))
                        .getCourse().get(stringCourses.indexOf(tvCourse.getText().toString()))
                        .getTeachingCalendarTime();
        for (int i = 0; i < calendars.size(); i++) {
            stringCalendars.add(calendars.get(i).getTime());
        }
        new MaterialDialog.Builder(this)
                .title("选择教学日历")
                .items(stringCalendars)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i,
                                            CharSequence calendar) {
                        tvTeachingDate.setText(calendar);
                        teachingCalendar = calendar.toString();
                    }
                }).show();

    }

    private void inputClassroom() {
        new MaterialDialog.Builder(this)
                .negativeText("取消")
                .title("输入教室")
                .inputRange(1, 15)
                .input(null, null, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog materialDialog, CharSequence classroom) {
                        tvClassroom.setText(classroom);
                    }
                })

                .show();
    }

    /**
     * 确认申请
     */
    private void confirm() {
        if (TextUtils.isEmpty(teacherCollege)) {
            SnackbarUtils.showShort(toolbar, "请选择学院");
            return;
        }
        if (TextUtils.isEmpty(requestedPerson)) {
            SnackbarUtils.showShort(toolbar, "请选择教师");
            return;
        }
        if (TextUtils.isEmpty(courseName)) {
            SnackbarUtils.showShort(toolbar, "请选择课程");
            return;
        }
        if (TextUtils.isEmpty(teachingCalendar)) {
            SnackbarUtils.showShort(toolbar, "请选择教学日历");
            return;
        }
        String classroom = tvClassroom.getText().toString().trim();
        String requestExplain = etApplyLanguage.getText().toString().trim();

        if (requestExplain.length() > 20) {
            SnackbarUtils.showShort(toolbar, "申请语不得超过20个字符");
            return;
        }
        presenter.apply(requestedPerson, teacherCollege, courseId, courseName, teachingCalendar,
                classroom, requestExplain);
    }

    /**
     * 测试接口
     */
    private void test() {
        Map<String, String> map = new HashMap<>();
        map.put("teacherId", "1001");
        map.put("token", SharedPreferencesUtils.getToken());
        map.put("schoolId", SharedPreferencesUtils.getSchoolId());
        OkHttpUtils.post().url("http://192.168.0.115:8080/rest/page/evaluateBySupervisor/selectAll")
                .params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("HTTP", response.toString());
            }
        });
    }

    @Override
    public void showError(Throwable e) {
        progressDialog.dismiss();
        SnackbarUtils.showShort(toolbar, e.getMessage());
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showEvaluateCourses(List<EvaluateCourse> courses) {
        this.courses = courses;
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        new MaterialDialog.Builder(this)
                .title("温馨提示")
                .positiveText("确定")
                .content("请求发起成功，请在我的任务里面查看")
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .show();
    }

}
