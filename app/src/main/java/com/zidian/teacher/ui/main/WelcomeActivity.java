package com.zidian.teacher.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zidian.teacher.BuildConfig;
import com.zidian.teacher.R;
import com.zidian.teacher.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 更新版本名后的欢迎界面
 * Created by GongCheng on 2017/5/9.
 */

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.vp_welcome)
    ViewPager vpWelcome;
    @BindView(R.id.indicator_page1)
    View indicatorPage1;
    @BindView(R.id.indicator_page2)
    View indicatorPage2;
    @BindView(R.id.tv_start)
    TextView tvStart;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.VERSION_NAME.equals(SharedPreferencesUtils.getVersionName())) {
            startActivity(new Intent(this, LoadActivity.class));
            finish();
        } else {
            setContentView(R.layout.activity_welcome);
            initView();
        }
    }

    private void initView() {
        unbinder = ButterKnife.bind(this);
        SharedPreferencesUtils.setVersionName(BuildConfig.VERSION_NAME);
        WelcomePagerAdapter pagerAdapter = new WelcomePagerAdapter(getSupportFragmentManager());
        vpWelcome.setAdapter(pagerAdapter);
        vpWelcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    tvStart.setVisibility(View.VISIBLE);
                    indicatorPage1.setSelected(false);
                    indicatorPage2.setSelected(true);
                } else {
                    tvStart.setVisibility(View.GONE);
                    indicatorPage1.setSelected(true);
                    indicatorPage2.setSelected(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.tv_start)
    public void startLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
            ImageView ivWelcome = (ImageView) rootView.findViewById(R.id.iv_welcome);
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                ivWelcome.setImageResource(R.drawable.bg_welcome1);
            } else {
                ivWelcome.setImageResource(R.drawable.bg_welcome2);
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
            }
            return null;
        }
    }
}
