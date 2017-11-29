package com.airxiao.o.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airxiao.o.R;
import com.airxiao.o.adapter.WelfareRecyclerViewAdapter;
import com.airxiao.o.base.BaseFragment;
import com.airxiao.o.entity.GankResBean;
import com.airxiao.o.mvp.welfare.WelfarePresenter;
import com.airxiao.o.mvp.welfare.WelfareView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaoyunlou on 17/10/22.
 */

public class WelfareFragment extends BaseFragment<WelfarePresenter> implements WelfareView {

    private WelfareRecyclerViewAdapter mWelfareRecyclerViewAdapter;
    private boolean isPrepared = false;
    private boolean mIsFirst = true;
    private static final String mType = "福利";
    // 开始请求的角标
    private int mStart = 1;
    // 一次请求的数量
    private int mCount = 10;
    List<GankResBean.ResultsBean> mDataList = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected WelfarePresenter createPresenter() {
        return new WelfarePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welfare, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setupRefreshLayout();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mWelfareRecyclerViewAdapter = new WelfareRecyclerViewAdapter(getActivity(), mDataList);
        recyclerView.setAdapter(mWelfareRecyclerViewAdapter);
    }

    private void setupRefreshLayout() {
        //设置 Header 为 Material样式
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mStart = 1;
                loadCustomData();
                refreshlayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mStart ++;
                loadCustomData();
                refreshlayout.finishLoadmore();
            }
        });
    }

    private void initData() {
        isPrepared = true;
        loadData();
    }

    @Override
    protected void loadData() {
        super.loadData();
        if (mIsVisible && mIsFirst && isPrepared) {
            refreshLayout.autoRefresh();
        }
    }

    private void loadCustomData() {
        mIsFirst = false;
        mvpPresenter.loadGankData(mType, mStart, mCount);
    }


    @Override
    public void getDataSuccess(List<GankResBean.ResultsBean> list, int mStart) {
        if (mStart == 1) {
            if (list != null) {
                if (mWelfareRecyclerViewAdapter == null) {
                    mWelfareRecyclerViewAdapter = new WelfareRecyclerViewAdapter(getActivity(), mDataList);
                }

                mDataList.clear();
                mDataList.addAll(list);
                mWelfareRecyclerViewAdapter.notifyDataSetChanged();
            }
        } else {
            mDataList.addAll(list);
            mWelfareRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }
}
