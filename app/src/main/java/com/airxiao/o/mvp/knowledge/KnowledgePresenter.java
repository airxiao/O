package com.airxiao.o.mvp.knowledge;

import com.airxiao.o.base.BasePresenter;

/**
 * Created by xiaoyunlou on 17/10/30.
 */

public class KnowledgePresenter extends BasePresenter<KnowledgeView> {

    public KnowledgePresenter(KnowledgeView knowledgeView) {
        attachView(knowledgeView);
    }

    public void loadGankData() {
        mvpView.showLoading();


    }


}
