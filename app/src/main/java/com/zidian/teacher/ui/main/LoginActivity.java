package com.zidian.teacher.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.School;
import com.zidian.teacher.presenter.LoginPresenter;
import com.zidian.teacher.presenter.contract.LoginContract;
import com.zidian.teacher.ui.widget.ClearEditText;
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
    @BindView(R.id.sp_school)
    Spinner spSchool;

    @Inject
    LoginPresenter presenter;

    private ProgressDialog progressDialog;
    private ArrayAdapter<School> arrayAdapter;
    private List<School> schools;
    private int schoolId;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);
        presenter.attachView(this);
        schools = new ArrayList<>();
        schools.add(new School(getString(R.string.select_school_please), 0));
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.login_loading));
        etUsername.setText(SharedPreferencesUtils.getUserName());
        etPassword.setText(SharedPreferencesUtils.getPassword());
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, schools);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSchool.setAdapter(arrayAdapter);
        spSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    schoolId = schools.get(position).getSchoolId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        presenter.getSchools();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        checkNotNull(presenter);
        presenter.deAttachView();
    }

    @OnClick(R.id.ll_select_school)
    public void getSchools() {
        if (schools.size() <= 1) {
            presenter.getSchools();
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
        presenter.login(username, password, String.valueOf(schoolId));
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
    public void showSchool(List<School> schools) {
        this.schools.addAll(schools);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        startActivity(new Intent(this, MainActivity.class));
    }

}
