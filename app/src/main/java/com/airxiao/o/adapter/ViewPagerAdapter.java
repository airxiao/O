package com.airxiao.o.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airxiao.o.R;
import com.airxiao.o.utils.ImgLoadUtil;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xiaoyunlou on 17/12/3.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> imgUrlList;

    public ViewPagerAdapter(List<String> imgUrlList, Context mContext) {
        this.imgUrlList = imgUrlList;
        this.mContext = mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.big_image_item, container, false);
        final PhotoView photoView = (PhotoView) view.findViewById(R.id.photoView);
        ImgLoadUtil.displayImage(imgUrlList.get(position), photoView);
        photoView.setOnPhotoTapListener((PhotoViewAttacher.OnPhotoTapListener) mContext);
        container.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        if (imgUrlList == null) {
            return 0;
        }
        return imgUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
