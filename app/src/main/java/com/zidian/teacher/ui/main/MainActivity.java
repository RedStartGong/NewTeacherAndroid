package com.zidian.teacher.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.ui.widget.BottomBar;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    private static final int COURSE = 0;
    private static final int EVALUATE = 1;
    private static final int QUESTIONAIRE = 2;
    private static final int MINE = 3;

    private SupportFragment[] supportFragments = new SupportFragment[4];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {

        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initViewAndData() {

    }
}
