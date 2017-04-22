package com.yao.mvpdemo.view;

import com.yao.mvpdemo.entity.Book;

/**
 * Created by yao on 2017/4/22.
 */

public interface IBookView extends IView {

    void onSuccess(Book book);

    void onError(String result);
}
