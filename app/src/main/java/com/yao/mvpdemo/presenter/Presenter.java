package com.yao.mvpdemo.presenter;


import com.yao.mvpdemo.view.IView;

/**
 * Created by yao on 2017/4/22.
 */

public interface Presenter {


    void stop();


    void attachView(IView view);
}
