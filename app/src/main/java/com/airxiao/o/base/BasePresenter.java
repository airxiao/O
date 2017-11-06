package com.airxiao.o.base;

import com.airxiao.o.retrofit.ApiClient;
import com.airxiao.o.retrofit.ApiStores;

import io.reactivex.Observable;
import io.reactivex.Observer;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by xiaoyunlou on 17/10/18.
 */

public class BasePresenter<V> {
    public V mvpView;
    protected ApiStores apiStores;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiStores  = ApiClient.retrofit().create(ApiStores.class);
    }

    public void deachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Observer observer) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
//        mCompositeSubscription.add( observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer));
    }

}
