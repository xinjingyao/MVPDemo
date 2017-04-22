package com.yao.mvpdemo.api;

import com.yao.mvpdemo.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yao on 2017/4/22.
 */

public interface ApiService {

    @GET("book/search")
    Observable<Book> getSearchBook(@Query("q") String name, @Query("tag") String tag,
                                   @Query("start") int start, @Query("count") int count);
}
