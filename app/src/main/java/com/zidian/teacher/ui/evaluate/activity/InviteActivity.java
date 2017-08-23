package com.zidian.teacher.ui.evaluate.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.CoursePlan;
import com.zidian.teacher.model.entity.remote.EvaCourse;
import com.zidian.teacher.model.entity.remote.InviteCourseResult;
import com.zidian.teacher.presenter.InvitePresenter;
import com.zidian.teacher.presenter.contract.InviteContract;
import com.zidian.teacher.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * 邀请评价
 * Created by GongCheng on 2017/4/14.
 */
public class InviteActivity extends BaseActivity implements InviteContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_invite_course)
    TextView tvInviteCourse;
    @BindView(R.id.tv_invite_teaching_date)
    TextView tvInviteTeachingDate;
    @BindView(R.id.tv_invite_teacher_name)
    TextView tvInviteTeacherName;
    @BindView(R.id.et_invitation_language)
    AppCompatEditText etInvitationLanguage;
    @BindView(R.id.til_invitation_language)
    TextInputLayout tilInvitationLanguage;

    @Inject
    InvitePresenter presenter;

    private static final int REQUEST_TEACHER = 1;

    private ProgressDialog progressDialog;
    private List<EvaCourse> evaCourses;
    private List<String> stringCourses;
    private List<CoursePlan> coursePlans;


    @Override
    protected int getLayout() {
        return R.layout.activity_invite;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        evaCourses = new ArrayList<>();
        stringCourses = new ArrayList<>();
        coursePlans = new ArrayList<>();
        toolbar.setTitle("邀请");
        setToolbarBack(toolbar);
        tilInvitationLanguage.setCounterEnabled(true);
        tilInvitationLanguage.setCounterMaxLength(20);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在提交邀请...");

        checkNotNull(presenter);
        presenter.attachView(this);
        presenter.getCourses();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @OnClick({R.id.ll_choose_course, R.id.ll_choose_teaching_date, R.id.ll_invite_teacher, R.id.btn_invite_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_choose_course:
                chooseCourse();
                break;
            case R.id.ll_choose_teaching_date:
                chooseTeachingCalendar();
                break;
            case R.id.ll_invite_teacher:
                startActivityForResult(new Intent(this, InviteSelectTeacherActivity.class), REQUEST_TEACHER);
                break;
            case R.id.btn_invite_confirm:
                confirm();
                break;
        }
    }

    private String college;
    private String json;
    private int courseId;
    private String courseName;
    private String teachingDate;

    /**
     * 选择课程
     */
    private void chooseCourse() {
        if (evaCourses == null || evaCourses.isEmpty()) {
            SnackbarUtils.showShort(toolbar, "当前没有可选课程");
            return;
        }
        if (stringCourses == null || stringCourses.isEmpty()) {
            for (int i = 0; i < evaCourses.size(); i++) {
                stringCourses.add(evaCourses.get(i).getCourseName());
            }
        }
        new MaterialDialog.Builder(this)
                .items(stringCourses)
                .title("请选择课程")
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i,
                                            CharSequence course) {
                        //设置选择的课程
                        tvInviteCourse.setText(course);
                        courseName = course.toString();
                        //设置选择学院的Id(方法同上)
                        courseId = evaCourses.get(i).getCourseId();
                        presenter.getCoursePlans(courseId);
                    }
                })
                .show();
    }

    private int coursePlanId;
    /**
     * 选择课堂
     */
    private void chooseTeachingCalendar() {
        if (coursePlans.isEmpty()) {
            SnackbarUtils.showShort(toolbar, "请先选择课程");
            chooseCourse();
        } else {
            new MaterialDialog.Builder(this)
                    .items(coursePlans)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog materialDialog, View view, int i,
                                                CharSequence teachingCalendar) {
                            tvInviteTeachingDate.setText(teachingCalendar);
                            coursePlanId = coursePlans.get(i).getCoursePlanId();
                            teachingDate = teachingCalendar.toString();
                        }
                    })
                    .show();
        }
    }

    /**
     * 根据activity返回的结果选择教师
     *
     * @param requestCode requestCode
     * @param resultCode  resultCode
     * @param data        返回intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TEACHER && resultCode == RESULT_OK) {
            json = data.getStringExtra("teachers");
            tvInviteTeacherName.setText("已添加");
        }
    }

    /**
     * 确认
     */
    private void confirm() {
        if (TextUtils.isEmpty(json)) {
            SnackbarUtils.showShort(toolbar, "请选择老师");
            return;
        }
        if (TextUtils.isEmpty(courseName)) {
            SnackbarUtils.showShort(toolbar, "请选择课程");
            return;
        }
        if (TextUtils.isEmpty(tvInviteTeachingDate.getText())) {
            SnackbarUtils.showShort(toolbar, "请选择教学日历");
            return;
        }
        String invitationLanguage = etInvitationLanguage.getText().toString().trim();
        if (invitationLanguage.length() > 20) {
            SnackbarUtils.showShort(toolbar, "邀请语不能超过20个字符");
            return;
        }

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
    public void showEvaCourses(List<EvaCourse> evaCourses) {
        this.evaCourses = evaCourses;
    }

    @Override
    public void showCoursePlans(List<CoursePlan> coursePlans) {
        this.coursePlans = coursePlans;
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        new MaterialDialog.Builder(this)
                .title("温馨提示")
                .positiveText("确定")
                .content("邀请发起成功，请在我的任务里面查看")
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                })
                .show();
    }
}
