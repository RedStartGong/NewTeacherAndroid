package com.zidian.teacher.model.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 自定义 Interceptor 对返回的 HTTP 状态码进行处理
 * Created by GongCheng on 2017/5/8.
 */

public class ResponseCodeInterceptor implements Interceptor {

    public ResponseCodeInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() != 200) {
            Log.e("HTTP", "-->" + response.code());
            throw new ApiException("服务器出现了一个异常");
        } else {
            Log.e("HTTP", "-->" + response.code());
        }
        return response;
    }
}
