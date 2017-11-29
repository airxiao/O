package com.airxiao.o.mvp.welfare;

import com.airxiao.o.base.BasePresenter;
import com.airxiao.o.entity.GankResBean;
import com.airxiao.o.retrofit.ApiCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaoyunlou on 17/11/29.
 */

public class WelfarePresenter extends BasePresenter<WelfareView> {

    public WelfarePresenter(WelfareView welfareView) {
        attachView(welfareView);
    }

    public void loadGankData(String mType, final int mStart, int mCount) {
//        mvpView.showLoading();
        apiStores.getGankIoData(mType, mStart, mCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiCallback<GankResBean>() {
                    @Override
                    public void onSuccess(GankResBean model) {
                        if (!model.isError() && model.getResults() != null) {
                            mvpView.getDataSuccess(model.getResults(), mStart);
                        } else {
                            mvpView.getDataFail("获取数据失败");
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
//                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }
                });

    }

}
