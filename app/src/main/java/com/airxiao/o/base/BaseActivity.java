package com.airxiao.o.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by xiaoyunlou on 17/10/18.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mvpPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutID());
        ButterKnife.bind(this);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    protected abstract int setLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.deachView();
        }
    }

    public void showLoading(String str) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(str);
        progressDialog.show();
    }

    public void showLoading() {
        progressDialog = new ProgressDialog(this);
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
