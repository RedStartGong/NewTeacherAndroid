package com.zidian.teacher.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zidian.teacher.R;

/**
 * Created by GongCheng on 2017/4/7.
 */

public abstract class BaseBackFragment extends BaseFragment {
    protected void initToolbarNav(@NonNull Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }
}
