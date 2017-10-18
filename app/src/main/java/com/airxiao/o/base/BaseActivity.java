package com.airxiao.o.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by xiaoyunlou on 17/10/18.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutID());
        ButterKnife.bind(this);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    protected abstract int layoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.deachView();
        }
    }


}
