package com.airxiao.o.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airxiao.o.R;
import com.airxiao.o.adapter.FragmentAdapter;
import com.airxiao.o.base.BaseFragment;
import com.airxiao.o.mvp.study.StudyPresenter;
import com.airxiao.o.mvp.study.StudyView;

import butterknife.BindView;

/**
 * Created by xiaoyunlou on 17/10/22.
 */

public class StudyFragment extends BaseFragment<StudyPresenter> implements StudyView{

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();


    }

    @Override
    protected StudyPresenter createPresenter() {
        return new StudyPresenter(this);
    }

    public void initUI() {
        setupViewPager();
        tabs.setupWithViewPager(viewpager);
    }

    private void setupViewPager() {
        if (viewpager != null) {
            FragmentAdapter adapter = new FragmentAdapter(getActivity().getSupportFragmentManager());
            adapter.addFragment(KnowledgeFragment.newInstance("all"), "全部");
            adapter.addFragment(KnowledgeFragment.newInstance("Android"), "Android");
            adapter.addFragment(KnowledgeFragment.newInstance("IOS"), "IOS");
            adapter.addFragment(KnowledgeFragment.newInstance("前端"), "前端");
            viewpager.setAdapter(adapter);
        }
    }

}
