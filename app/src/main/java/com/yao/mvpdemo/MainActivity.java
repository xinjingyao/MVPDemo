package com.yao.mvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yao.mvpdemo.entity.Book;
import com.yao.mvpdemo.presenter.BookPresenter;
import com.yao.mvpdemo.presenter.Presenter;
import com.yao.mvpdemo.view.IBookView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn;
    private BookPresenter mPresenter;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new BookPresenter(this);
        mPresenter.attachView(mBookView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getSearchBooks("金瓶梅", null, 0, 1);
            }
        });
    }

    private IBookView mBookView = new IBookView() {
        @Override
        public void onSuccess(Book book) {
            tv.setText(book.toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void startDialog() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void stopDialog() {
            mProgressBar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }
}
