package com.airxiao.o.mvp.knowledge;

import com.airxiao.o.base.BaseView;
import com.airxiao.o.entity.GankResBean;

import java.util.List;

/**
 * Created by xiaoyunlou on 17/10/30.
 */

public interface KnowledgeView extends BaseView {

    void getDataSuccess(List<GankResBean.ResultsBean> list, int mStart);

    void getDataFail(String msg);
}
