package com.zidian.teacher.ui.mine.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseBackFragment;
import com.zidian.teacher.presenter.ChangePasswordPresenter;
import com.zidian.teacher.presenter.contract.ChangePasswordContract;
import com.zidian.teacher.ui.main.activity.LoginActivity;
import com.zidian.teacher.util.ActManager;
import com.zidian.teacher.util.SharedPreferencesUtils;
import com.zidian.teacher.util.SnackbarUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/4/7.
 */

public class ChangePasswordFragment extends BaseBackFragment implements
        ChangePasswordContract.View, TextWatcher{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.til_old)
    TextInputLayout tilOld;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.til_new)
    TextInputLayout tilNew;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.til_confirm)
    TextInputLayout tilConfirm;

    @Inject
    ChangePasswordPresenter presenter;
    @Inject
    ActManager actManager;

    private ProgressDialog progressDialog;

    public static ChangePasswordFragment newInstance() {

        Bundle args = new Bundle();

        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_change_password;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle(R.string.mine_change_password);
        initToolbarNav(toolbar);
        checkNotNull(presenter);
        presenter.attachView(this);
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getString(R.string.change_password_loading));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        checkNotNull(presenter);
        presenter.deAttachView();
    }

    private void changePassword() {
        String oldPassword = etOldPassword.getText().toString().trim();
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        etOldPassword.addTextChangedListener(this);
        etNewPassword.addTextChangedListener(this);
        etConfirmPassword.addTextChangedListener(this);
        if (oldPassword.length() == 0) {
            tilOld.setErrorEnabled(true);
            tilOld.setError(getString(R.string.please_input_old_password));
            return;
        }
        if (newPassword.length() == 0) {
            tilNew.setErrorEnabled(true);
            tilNew.setError(getString(R.string.please_input_new_password));
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            tilConfirm.setErrorEnabled(true);
            tilConfirm.setError(getString(R.string.entered_password_diff));
            return;
        }

        presenter.changePassword(oldPassword, newPassword, confirmPassword);
    }

    @OnClick(R.id.btn_confirm)
    public void confirm() {
        changePassword();
    }

    @OnClick(R.id.btn_cancel)
    public void cancel() {
        onBackPressedSupport();
    }

    @Override
    public void showError(Throwable e) {
        progressDialog.dismiss();
        SnackbarUtils.showShort(tilNew, e.getMessage());
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        pop();
        checkNotNull(actManager);
        actManager.finishAllActivity();
        SharedPreferencesUtils.clearAll();
        startActivity(new Intent(activity, LoginActivity.class));
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        tilOld.setErrorEnabled(false);
        tilNew.setErrorEnabled(false);
        tilConfirm.setErrorEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
