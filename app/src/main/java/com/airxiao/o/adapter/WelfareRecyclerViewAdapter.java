package com.airxiao.o.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airxiao.o.R;
import com.airxiao.o.entity.GankResBean;

import java.util.List;

/**
 * Created by xiaoyunlou on 17/11/29.
 */

public class WelfareRecyclerViewAdapter extends RecyclerView.Adapter<WelfareRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<GankResBean.ResultsBean> list;

    public WelfareRecyclerViewAdapter(Context mContext, List<GankResBean.ResultsBean> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.welfare_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_welfare;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_welfare = (ImageView) itemView.findViewById(R.id.iv_welfare);

        }
    }
}
