package com.airxiao.o.base;

import com.airxiao.o.retrofit.ApiClient;
import com.airxiao.o.retrofit.ApiStores;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by xiaoyunlou on 17/10/18.
 */

public class BasePresenter<V> {
    public V mvpView;
    protected ApiStores apiStores;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiStores  = ApiClient.retrofit().create(ApiStores.class);
    }

    public void deachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(disposable);
    }

}
