package com.zidian.teacher.ui.evaluate.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.College;
import com.zidian.teacher.model.entity.remote.CoursePlan;
import com.zidian.teacher.model.entity.remote.EvaCourse;
import com.zidian.teacher.model.entity.remote.EvaTeacher;
import com.zidian.teacher.model.entity.remote.EvaluateCourse;
import com.zidian.teacher.presenter.ApplyToEvaPresenter;
import com.zidian.teacher.presenter.contract.ApplyToEvaContract;
import com.zidian.teacher.util.SnackbarUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * 申请评价别人
 * Created by GongCheng on 2017/4/13.
 */

public class ApplyToEvaActivity extends BaseActivity implements ApplyToEvaContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_college)
    TextView tvCollege;
    @BindView(R.id.tv_teacher_name)
    TextView tvTeacherName;
    @BindView(R.id.tv_course)
    TextView tvCourse;
    @BindView(R.id.tv_teaching_date)
    TextView tvTeachingDate;
    @BindView(R.id.til_apply_language)
    TextInputLayout tilApplyLanguage;
    @BindView(R.id.et_apply_language)
    EditText etApplyLanguage;

    @Inject
    ApplyToEvaPresenter presenter;

    private MaterialDialog progressDialog;
    private List<College> colleges;
    private List<EvaTeacher> evaTeachers;
    private List<EvaCourse> evaCourses;
    private List<CoursePlan> coursePlans;

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
        colleges = new ArrayList<>();
        evaTeachers = new ArrayList<>();
        evaCourses = new ArrayList<>();
        coursePlans = new ArrayList<>();

        progressDialog = new MaterialDialog.Builder(this)
                .progress(true, 10)
                .content("加载中...")
                .build();
        toolbar.setTitle("同行评价-评价他人");
        tilApplyLanguage.setCounterEnabled(true);
        tilApplyLanguage.setCounterMaxLength(20);
        setToolbarBack(toolbar);
        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getColleges();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @OnClick({R.id.ll_choose_college, R.id.ll_choose_teacher, R.id.ll_choose_course,
            R.id.ll_choose_teaching_date, R.id.btn_confirm})
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
            case R.id.btn_confirm:
                confirm();
                break;
        }
    }

    private int collegeId;
    private int evaTeacherId;
    private int courseId;
    private int coursePlanId;
    /**
     * 选择学院
     */
    private void chooseCollege() {
        if (colleges.isEmpty()) {
            SnackbarUtils.showShort(toolbar, "当前无可选学院，请重试");
            return;
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
                        collegeId = colleges.get(i).getCollegeId();
                        //获取教师
                        evaTeachers.clear();
                        presenter.getEvaTeachers(collegeId);
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
        if (evaTeachers.isEmpty()) {
            SnackbarUtils.showShort(toolbar, "无可选教师");
            return;
        }
        new MaterialDialog.Builder(this)
                .title("选择教师")
                .items(evaTeachers)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence teacherName) {
                        tvTeacherName.setText(teacherName);
                        tvCourse.setText("");
                        tvTeachingDate.setText("");
                        evaTeacherId = evaTeachers.get(i).getTeacherId();
                        //获取课程
                        evaCourses.clear();
                        presenter.getEvaCourses(evaTeacherId);
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
        if (evaCourses.isEmpty()) {
            SnackbarUtils.showShort(toolbar, "无可选课程");
            return;
        }
        new MaterialDialog.Builder(this)
                .title("选择课程")
                .items(evaCourses)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view,
                                            int i, CharSequence course) {
                        tvCourse.setText(course);
                        tvTeachingDate.setText("");
                        courseId = evaCourses.get(i).getCourseId();
                        //获取课堂
                        coursePlans.clear();
                        presenter.getEvaCoursePlans(evaTeacherId, courseId);
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
        if (coursePlans.isEmpty()) {
            SnackbarUtils.showShort(toolbar, "无可选课堂");
            return;
        }
        new MaterialDialog.Builder(this)
                .title("选择教学日历")
                .items(coursePlans)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i,
                                            CharSequence coursePlan) {
                        tvTeachingDate.setText(coursePlan);
                        coursePlanId = coursePlans.get(i).getCoursePlanId();
                    }
                }).show();
    }


    /**
     * 确认申请
     */
    private void confirm() {
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
        if (TextUtils.isEmpty(tvTeachingDate.getText())) {
            SnackbarUtils.showShort(toolbar, "请选择教学日历");
            return;
        }
        String requestExplain = etApplyLanguage.getText().toString().trim();

        if (requestExplain.length() > 20) {
            SnackbarUtils.showShort(toolbar, "申请语不得超过20个字符");
            return;
        }
        presenter.apply(String.valueOf(evaTeacherId), requestExplain, 2, coursePlanId);
    }


    @Override
    public void showError(Throwable e) {
        progressDialog.dismiss();
        SnackbarUtils.showShort(toolbar, e.getMessage());
    }

    @Override
    public void showColleges(List<College> colleges) {
        this.colleges = colleges;
    }

    @Override
    public void showEvaTeachers(List<EvaTeacher> evaTeachers) {
        this.evaTeachers = evaTeachers;
    }

    @Override
    public void showEvaCourses(List<EvaCourse> evaCourses) {
        this.evaCourses = evaCourses;
    }

    @Override
    public void showEvaCoursePlans(List<CoursePlan> coursePlans) {
        this.coursePlans = coursePlans;
    }

    @Override
    public void showLoading() {
        progressDialog.show();
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
