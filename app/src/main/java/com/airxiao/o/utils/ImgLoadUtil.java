package com.airxiao.o.utils;

import android.widget.ImageView;

import com.airxiao.o.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;

/**
 * Created by xiaoyunlou on 17/10/29.
 */

public class ImgLoadUtil {


    public static void displayEspImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.img_one_bi_one)
                .error(R.drawable.img_one_bi_one)
//                .skipMemoryCache(true) //跳过内存缓存
//                .crossFade(1000)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)// 缓存图片源文件（解决加载gif内存溢出问题）
//                .into(new GlideDrawableImageViewTarget(imageView, 1));
                .into(imageView);
    }

    /**
     * 妹子图
     */
    public static void displayImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .crossFade(500)
                .placeholder(R.drawable.img_default_meizi)
                .error(R.drawable.img_default_meizi)
                .into(imageView);
    }

    public static void displayPhotoView(String url, ImageView imageView, RequestListener<String, GlideDrawable> listener) {
        Glide.with(imageView.getContext())
                .load(url)
                .crossFade(700)
                .listener(listener)
                .into(imageView);
    }

}
