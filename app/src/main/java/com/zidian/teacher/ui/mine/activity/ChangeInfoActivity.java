package com.zidian.teacher.ui.mine.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/7.
 */

public class ChangeInfoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_change_info;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle("编辑个人资料");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();

            }
        });
    }

}
