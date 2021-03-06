package com.airxiao.o.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airxiao.o.R;
import com.airxiao.o.entity.GankResBean;
import com.airxiao.o.ui.WebviewActivity;
import com.airxiao.o.utils.ImgLoadUtil;
import com.airxiao.o.utils.TimeUtil;

import java.util.List;

/**
 * Created by xiaoyunlou on 17/10/26.
 */

public class StudyRecyclerViewAdapter extends RecyclerView.Adapter<StudyRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<GankResBean.ResultsBean> list;
    private boolean isAll;

    public void setIsAll(boolean isAll) {
        this.isAll = isAll;
    }

    public StudyRecyclerViewAdapter(Context mContext, List<GankResBean.ResultsBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).getDesc());
        if (list.get(position).getImages() != null && list.get(position).getImages().size() > 0
                && !TextUtils.isEmpty(list.get(position).getImages().get(0))) {
            holder.iv_titlePic.setVisibility(View.VISIBLE);
            ImgLoadUtil.displayEspImage(list.get(position).getImages().get(0), holder.iv_titlePic);
        } else {
            holder.iv_titlePic.setVisibility(View.GONE);
        }

        if (list.get(position).getWho() != null) {
            holder.tv_who.setText(list.get(position).getWho());
        } else {
            holder.tv_who.setText(mContext.getString(R.string.no_name));
        }

        if (isAll) {
            holder.tv_type.setVisibility(View.VISIBLE);
            holder.tv_type.setText(" · " + list.get(position).getType());
        } else {
            holder.tv_type.setVisibility(View.GONE);
        }

        holder.tv_time.setText(TimeUtil.getTranslateTime(list.get(position).getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView iv_titlePic;
        TextView tv_who;
        TextView tv_type;
        TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_titlePic = (ImageView) itemView.findViewById(R.id.iv_titlePic);
            tv_who = (TextView) itemView.findViewById(R.id.tv_who);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebviewActivity.loadUrl(view.getContext(), list.get(getLayoutPosition()).getUrl(), "加载中...");
                }
            });
        }
    }
}
