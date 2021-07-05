package com.jowney.common.net;


import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMaster {
    private static final String TAG = "RetrofitMaster";
    private static RetrofitMaster mRetrofitMaster = null;
    private static Retrofit mRetrofit = null;
    //对外提供的Api接口
    private Object serverApi = null;

    public static RetrofitMaster getInstance() {
        if (mRetrofitMaster == null) {
            mRetrofitMaster = new RetrofitMaster();
        }
        return mRetrofitMaster;
    }
    //在Application里面初始化
    public <T> void init(String baseUrl, Class<T> tClass, List<Interceptor> interceptorList) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("Net_Log_Interceptor", "|" + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(logging);
        if (interceptorList != null && !interceptorList.isEmpty()) {
            while (interceptorList.iterator().hasNext()) {
                okBuilder.addInterceptor(interceptorList.iterator().next());
            }
        }
        OkHttpClient okHttpClient = okBuilder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl) // 设置网络请求的公共Url地址
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .build();
        serverApi = mRetrofit.create(tClass);
    }

    public Object getServerApi() {
        return serverApi;
    }
}