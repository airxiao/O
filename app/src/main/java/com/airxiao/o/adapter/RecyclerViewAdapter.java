package com.airxiao.o.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.airxiao.o.entity.KnowledageResBean;

import java.util.List;

/**
 * Created by xiaoyunlou on 17/10/26.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<KnowledageResBean> list;

    public RecyclerViewAdapter(Context mContext, List<KnowledageResBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
