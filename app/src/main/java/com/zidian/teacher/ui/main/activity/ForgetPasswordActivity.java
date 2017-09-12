package com.zidian.teacher.ui.main.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.util.SnackbarUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by GongCheng on 2017/9/12.
 */

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.tv_telephone)
    TextView tvTelephone;

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initViewAndData() {

    }

    @OnClick(R.id.btn_back)
    public void back() {
        onBackPressedSupport();
    }

    @OnClick(R.id.tv_telephone)
    public void getTelephoneNumber() {
        ClipboardManager copy = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        copy.setText("010-86466797");
        SnackbarUtils.showShort(tvTelephone, "电话号码已复制到剪贴板");
    }
}
