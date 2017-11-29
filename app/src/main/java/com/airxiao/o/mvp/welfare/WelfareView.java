package com.airxiao.o.mvp.welfare;

import com.airxiao.o.base.BaseView;
import com.airxiao.o.entity.GankResBean;

import java.util.List;

/**
 * Created by xiaoyunlou on 17/11/29.
 */

public interface WelfareView extends BaseView {

    void getDataSuccess(List<GankResBean.ResultsBean> list, int mStart);

    void getDataFail(String msg);

}
