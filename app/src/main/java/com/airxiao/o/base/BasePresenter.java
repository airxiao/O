package com.airxiao.o.base;

/**
 * Created by xiaoyunlou on 17/10/18.
 */

public class BasePresenter<V> {
    public V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;

    }

    public void deachView() {
        this.mvpView = null;
    }

}
