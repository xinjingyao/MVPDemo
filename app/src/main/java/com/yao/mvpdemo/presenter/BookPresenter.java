package com.yao.mvpdemo.presenter;

import android.content.Context;

import com.yao.mvpdemo.api.ApiManager;
import com.yao.mvpdemo.api.ApiService;
import com.yao.mvpdemo.entity.Book;
import com.yao.mvpdemo.network.RetrofitHelper;
import com.yao.mvpdemo.view.IBookView;
import com.yao.mvpdemo.view.IView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yao on 2017/4/22.
 */

public class BookPresenter implements Presenter {
    private Context mContext;
    private IBookView bookView;
    private CompositeSubscription mCompositeSubscription;
    private Book mBook;
    private ApiManager mApiManager;

    public BookPresenter(Context context) {
        mContext = context;
        mApiManager = new ApiManager(context);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void stop() {
        if (mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(IView view) {
        bookView = (IBookView) view;
    }

    /**
     * 搜索书籍列表
     * @param name 名字
     * @param tag 标签
     * @param start
     * @param count
     */
    public void getSearchBooks(String name, String tag, int start, int count) {
        bookView.startDialog();
        mCompositeSubscription.add(mApiManager.getSearchBooks(name, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (mBook != null) {
                            bookView.onSuccess(mBook);
                        }
                        bookView.stopDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        bookView.onError("请求失败！！");
                        bookView.stopDialog();
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                }));
    }
}
