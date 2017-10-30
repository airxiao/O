package com.airxiao.o.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by xiaoyunlou on 17/10/18.
 */

public abstract class BaseFragment<P extends BasePresenter>  extends Fragment {

    protected P mvpPresenter;
    public Activity mActivity;
    private ProgressDialog progressDialog;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mActivity = getActivity();
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.deachView();
        }
    }

    public void showLoading(String str) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(str);
        progressDialog.show();
    }

    public void showLoading() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中");
        progressDialog.show();
    }

    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }
}
