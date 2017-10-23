package com.airxiao.o.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.airxiao.o.R;
import com.airxiao.o.adapter.FragmentAdapter;
import com.airxiao.o.base.BaseActivity;
import com.airxiao.o.mvp.main.MainPresenter;
import com.airxiao.o.mvp.main.MainView;
import com.airxiao.o.view.NoScrollViewPager;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.noscrollviewpager)
    NoScrollViewPager noscrollviewpager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int setLayoutID() {
        return R.layout.activity_main;
    }

    public void initUI() {
        setupViewPager();

    }

    private void setupViewPager() {
        if (noscrollviewpager != null) {
            FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
            adapter.addFragment(new StudyFragment(), "干货");
            adapter.addFragment(new WelfareFragment(), "福利");
            noscrollviewpager.setAdapter(adapter);
        }
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

}
