package com.zidian.teacher.ui.mine.activity;

import android.content.pm.PackageManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/6.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;
    @BindView(R.id.tv_about)
    TextView tvAbout;

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle(getString(R.string.about));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
        String versionName;
        try {
            versionName = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
            tvVersionCode.setText(getString(R.string.version, versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tvAbout.setText(R.string.about_zidian);
    }

}
