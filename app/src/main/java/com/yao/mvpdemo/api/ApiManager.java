package com.yao.mvpdemo.api;

import android.content.Context;

import com.yao.mvpdemo.entity.Book;
import com.yao.mvpdemo.network.RetrofitHelper;

import rx.Observable;

/**
 * Created by yao on 2017/4/22.
 */

public class ApiManager {
    private ApiService mApiService;

    public ApiManager(Context context) {
        mApiService = RetrofitHelper.getInstance(context).getApiService();
    }

    public Observable<Book> getSearchBooks(String name, String tag, int start, int count) {
        return mApiService.getSearchBook(name, tag, start, count);
    }
}
