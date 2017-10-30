package com.airxiao.o.mvp.study;

import com.airxiao.o.base.BasePresenter;

/**
 * Created by xiaoyunlou on 17/10/30.
 */

public class StudyPresenter extends BasePresenter<StudyView> {

    public StudyPresenter(StudyView studyView) {
        attachView(studyView);
    }
}
