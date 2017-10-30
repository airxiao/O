package com.airxiao.o.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airxiao.o.R;
import com.airxiao.o.adapter.RecyclerViewAdapter;
import com.airxiao.o.base.BaseFragment;
import com.airxiao.o.mvp.knowledge.KnowledgePresenter;
import com.airxiao.o.mvp.knowledge.KnowledgeView;

import butterknife.BindView;

/**
 * Created by xiaoyunlou on 17/10/25.
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeView{

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private static final String TYPE = "Type";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

}
