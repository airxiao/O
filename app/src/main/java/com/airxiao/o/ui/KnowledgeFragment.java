package com.airxiao.o.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airxiao.o.R;
import com.airxiao.o.adapter.RecyclerViewAdapter;
import com.airxiao.o.base.BaseFragment;
import com.airxiao.o.mvp.knowledge.KnowledgePresenter;
import com.airxiao.o.mvp.knowledge.KnowledgeView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * Created by xiaoyunlou on 17/10/25.
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeView{

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private static final String TYPE = "Type";
    private boolean mIsFirst = true;

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
        initData();
        initView();
    }

    private void initData() {

    }

    // 只有当fragment可见并且没有过加载记录才可以加载
    @Override
    protected void loadData() {
        super.loadData();
        if (mIsVisible && mIsFirst) {
            mIsFirst = false;
            loadCustomData();
        }

    }

    private void loadCustomData() {
        mvpPresenter.loadGankData();
    }

    private void initView() {
        setupRefreshLayout();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    private void setupRefreshLayout() {
        refreshLayout.autoRefresh();
        //设置 Header 为 Material样式
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
            }
        });
    }

}
