package com.yao.mvpdemo.network;

import android.content.Context;

import com.yao.mvpdemo.api.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yao on 2017/4/22.
 */

public class RetrofitHelper {
    private Context mContext;
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    private OkHttpClient mOkHttp = null;

    private RetrofitHelper(Context context) {
        mContext = context;
        init();
    }

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    private void init() {
        resetRetrofit();
    }

    private void resetRetrofit() {
        mOkHttp = new OkHttpClient();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mOkHttp)
                .build();
//        mRetrofit.create(ApiService.class);
    }

    /**
     * 可以另建一个ApiManager管理APi
     * @return
     */
    public ApiService getApiService() {
        return mRetrofit.create(ApiService.class);
    }
}
