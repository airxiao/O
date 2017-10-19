package com.airxiao.o.mvp.main;

import com.airxiao.o.base.BasePresenter;

/**
 * Created by xiaoyunlou on 17/10/19.
 */

public class MainPresenter extends BasePresenter<MainView>{

    public MainPresenter(MainView mainView) {
        attachView(mainView);
    }
}
