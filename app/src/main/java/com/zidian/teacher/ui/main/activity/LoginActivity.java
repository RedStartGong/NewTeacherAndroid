package com.zidian.teacher.ui.main.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.School;
import com.zidian.teacher.presenter.LoginPresenter;
import com.zidian.teacher.presenter.contract.LoginContract;
import com.zidian.teacher.ui.widget.ClearEditText;
import com.zidian.teacher.util.ActManager;
import com.zidian.teacher.util.SharedPreferencesUtils;
import com.zidian.teacher.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Activity for login
 * Created by GongCheng on 2017/4/1.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.et_username)
    ClearEditText etUsername;
    @BindView(R.id.et_password)
    ClearEditText etPassword;
    @BindView(R.id.tv_school)
    TextView tvSchool;

    @Inject
    LoginPresenter presenter;
    @Inject
    ActManager actManager;

    private static final int CHOOSE_SCHOOL = 1;
    private ProgressDialog progressDialog;
    private int schoolId;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
        actManager.addActivity(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);
        presenter.attachView(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.login_loading));
        etUsername.setText(SharedPreferencesUtils.getUserName());
        etPassword.setText(SharedPreferencesUtils.getPassword());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        checkNotNull(presenter);
        presenter.deAttachView();
    }

    @OnClick(R.id.ll_select_school)
    public void getSchools() {
        startActivityForResult(new Intent(this, ChooseSchoolActivity.class), CHOOSE_SCHOOL);
    }
    /**
     * {@link ChooseSchoolActivity} 返回值
     *
     * @param requestCode requestCode
     * @param resultCode  resultCode
     * @param data        data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_SCHOOL && resultCode == RESULT_OK && data != null) {
            School.SchoolBean bean = (School.SchoolBean) data.getSerializableExtra("school");
            tvSchool.setText(bean.getSchoolName());
            schoolId = bean.getSchoolId();
        }
    }

    @OnClick(R.id.btn_login)
    public void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            SnackbarUtils.showShort(etUsername, getString(R.string.input_username_please));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            SnackbarUtils.showShort(etUsername, getString(R.string.input_password_please));
            return;
        }
        if (schoolId == 0) {
            SnackbarUtils.showShort(etUsername, getString(R.string.select_school_please));
            return;
        }
        presenter.login(username, password, schoolId);
    }

    @Override
    public void showError(Throwable e) {
        progressDialog.dismiss();
        SnackbarUtils.showShort(etUsername, e.getMessage());
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }


    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        actManager.finishAllActivity();
        this.overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out);
        startActivity(new Intent(this, MainActivity.class));
    }

}
