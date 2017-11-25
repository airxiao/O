package com.airxiao.o.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airxiao.o.R;
import com.airxiao.o.adapter.RecyclerViewAdapter;
import com.airxiao.o.base.BaseFragment;
import com.airxiao.o.entity.KnowledageResBean;
import com.airxiao.o.mvp.knowledge.KnowledgePresenter;
import com.airxiao.o.mvp.knowledge.KnowledgeView;
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
 * Created by xiaoyunlou on 17/10/25.
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeView{

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private static final String TYPE = "Type";
    private String mType = "";
    private boolean mIsFirst = true;
    private boolean isPrepared = false;
    // 开始请求的角标
    private int mStart = 1;
    // 一次请求的数量
    private int mCount = 10;
    List<KnowledageResBean.ResultsBean> mDataList = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public static KnowledgeFragment newInstance(String type) {
        KnowledgeFragment fragment = new KnowledgeFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_knowledage, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        if (getArguments() != null) {
            mType = getArguments().getString(TYPE);
            if (TextUtils.equals(mType, "all")) {
                mRecyclerViewAdapter.setIsAll(true);
            } else {
                mRecyclerViewAdapter.setIsAll(false);
            }
        }

        isPrepared = true;
        loadData();
    }

    // 只有当fragment可见并且没有过加载记录才可以加载
    // 由于 setUserVisibleHint 方法早于 onActivityCreated，所以设置 isPrepared 为 false，避免loadCustomData 中 mvpPresenter对象为空
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

    private void initView() {
        setupRefreshLayout();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDataList);
        recyclerView.setAdapter(mRecyclerViewAdapter);
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

    @Override
    public void getDataSuccess(List<KnowledageResBean.ResultsBean> list, int mStart) {
        if (mStart == 1) {
            if (list != null) {
                if (mRecyclerViewAdapter == null) {
                    mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDataList);
                    if (TextUtils.equals(mType, "all")) {
                        mRecyclerViewAdapter.setIsAll(true);
                    } else {
                        mRecyclerViewAdapter.setIsAll(false);
                    }
                }

                mDataList.clear();
                mDataList.addAll(list);
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        } else {
            mDataList.addAll(list);
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }
}
