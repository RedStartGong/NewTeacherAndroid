package com.zidian.teacher.model.network;

import android.util.Log;

import com.zidian.teacher.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Service Factory
 * Created by GongCheng on 2017/3/16.
 */

public final class ServiceFactory {
    private static final String TAG = "HTTP";
    /**
     *  release： http://103.231.69.80:80//rest/
     *  debug：http://192.168.0.115:8080//rest/
     */
    private static final String BASE_URL = "http://192.168.0.115:8080//rest/";

    public static TeacherService makeStudentService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(makeOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TeacherService.class);
    }

    private static OkHttpClient makeOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(makeLoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, message);
            }
        });
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }
}
