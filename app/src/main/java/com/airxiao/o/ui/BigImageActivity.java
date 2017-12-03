package com.airxiao.o.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.airxiao.o.R;
import com.airxiao.o.adapter.ViewPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xiaoyunlou on 17/12/3.
 */

public class BigImageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, PhotoViewAttacher.OnPhotoTapListener {

    // 接收传过来的uri地址
    private List<String> imgUrlList;
    // 接收穿过来当前选择的图片的数量
    private int code;
    private ViewPagerAdapter adapter;

    @BindView(R.id.tv_page)
    TextView tv_page;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        code = bundle.getInt("code");
        imgUrlList = bundle.getStringArrayList("imgUrlList");
        /**
         * 给viewpager设置适配器
         */
        adapter = new ViewPagerAdapter(imgUrlList, this);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(code);
        viewpager.setOnPageChangeListener(this);
        viewpager.setEnabled(false);
        tv_page.setText((code + 1) + " / " + imgUrlList.size());
    }


    /**
     * 本方法主要监听viewpager滑动的时候的操作
     */
    @Override
    public void onPageSelected(int position) {
        tv_page.setText((position + 1) + " / " + imgUrlList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    //点击图片
    @Override
    public void onPhotoTap(View view, float x, float y) {
        finish();
    }

    @Override
    public void onOutsidePhotoTap() {

    }
}
