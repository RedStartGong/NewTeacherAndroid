package com.zidian.teacher.ui.evaluate.activity;

import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by GongCheng on 2017/4/13.
 */

public class ApplyForEvaActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DataManager dataManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_apply_for_eva;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle("同行评价-评价他人");
        setToolbarBack(toolbar);
        Map<String, String> map = new HashMap<>();
        map.put("teacherId", "1001");
        map.put("token", SharedPreferencesUtils.getToken());
        map.put("schoolId", SharedPreferencesUtils.getSchoolId());
        OkHttpUtils.post().url("http://192.168.0.115:8080/rest/page/evaluateBySupervisor/selectAll")
                .params(map).build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("HTTP", response.toString());
            }
        });

    }
}
