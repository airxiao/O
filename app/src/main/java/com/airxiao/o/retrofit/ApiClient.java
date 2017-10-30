package com.airxiao.o.retrofit;

import com.airxiao.o.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WuXiaolong on 2016/3/24.
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public class ApiClient {
    private static long CONNECT_TIME = 5 * 1000;
    private static long READ_TIME = 4 * 1000;
    private static long WRITE_CONNECT_TIME = 4 * 1000;

    public static Retrofit mRetrofit;

    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }

            OkHttpClient okHttpClient = builder
                    .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                    .readTimeout(READ_TIME, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_CONNECT_TIME, TimeUnit.SECONDS)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

}
