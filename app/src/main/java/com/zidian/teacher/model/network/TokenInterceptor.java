package com.zidian.teacher.model.network;

import com.zidian.teacher.util.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by GongCheng on 2017/6/12.
 */

public class TokenInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        if (originalHttpUrl.toString().equals(ServiceFactory.BASE_URL + "page/shiro/loginTeacher")
                || originalHttpUrl.toString().equals(ServiceFactory.BASE_URL + "page/shiro/school")) {
            return chain.proceed(original);
        }
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("token", SharedPreferencesUtils.getToken())
                .addQueryParameter("teacherId", String.valueOf(SharedPreferencesUtils.getTeacherId()))
                .addQueryParameter("schoolId", String.valueOf(SharedPreferencesUtils.getSchoolId()))
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);
        Request request = requestBuilder.build();

        return chain.proceed(request);
    }
}
