package com.zidian.teacher.model.network;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 自定义 Interceptor 对返回的 HTTP 状态码进行处理
 * Created by GongCheng on 2017/5/8.
 */

public class ResponseCodeInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
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
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            Buffer buffer = source.buffer();
            source.request(Long.MAX_VALUE);
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            try {
                JSONObject jsonObject = new JSONObject(buffer.clone().readString(charset));
                int code = jsonObject.getInt("code");
                if (code == 202) {
                    //TODO 验证token
                    Log.e("HTTP", "朋友，token失效了");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
