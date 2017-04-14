package com.zidian.teacher.ui.evaluate.activity;

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

/**
 * Created by GongCheng on 2017/4/13.
 */

public class ApplyForEvaActivity extends BaseActivity {
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

//        final OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("teacherId", "1001")
//                .addFormDataPart("token", SharedPreferencesUtils.getToken())
//                .addFormDataPart("condition", "经济与管理学院")
//                .addFormDataPart("schoolId", "2")
//                .build();
//
//        final Request request = new Request.Builder()
//                .url("http://192.168.0.115:8080/rest/page/evaluateBySupervisor/selectTeacher")
//                .post(requestBody)
//                .build();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    okhttp3.Response response =  client.newCall(request).execute();
//                    Log.e("HTTP", response.body().toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        Map<String, String> map = new HashMap<>();
        map.put("teacherId", "1001");
        map.put("token", SharedPreferencesUtils.getToken());
        map.put("schoolId", SharedPreferencesUtils.getSchoolId());
        map.put("condition", "经济与管理学院");
        OkHttpUtils.post().url("http://192.168.0.115:8080/rest/page/evaluateBySupervisor/selectTeacher")
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
