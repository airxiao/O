package com.airxiao.o.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
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

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, NavigationView.OnNavigationItemSelectedListener {

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

    private static final String PROJECT_ADDRESS = "https://github.com/airxiao/O";
    private boolean isNight = false;

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
        setupDraweContent();
        tabs.setupWithViewPager(noscrollviewpager);
    }

    private void setupDraweContent() {
        if (navigation != null) {
            navigation.setNavigationItemSelectedListener(this);
        }
    }

    private void setupViewPager() {
        if (noscrollviewpager != null) {
            FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
            adapter.addFragment(new StudyFragment(), "干货");
            adapter.addFragment(new WelfareFragment(), "福利");
            noscrollviewpager.setAdapter(adapter);
            noscrollviewpager.setScroll(false);
            // 左右预加载页面的个数
            noscrollviewpager.setOffscreenPageLimit(1);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer_layout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.navHome:

                return true;
            case R.id.navProject:

                return true;
            case R.id.navScan:

                return true;
            case R.id.navGithubAdd:
                WebviewActivity.loadUrl(this, PROJECT_ADDRESS, "O");
                return true;
            case R.id.navMode:
                int nightMode;
                if (isNight) {
                    isNight = false;
                    item.setTitle("夜间模式");
                    nightMode = AppCompatDelegate.MODE_NIGHT_NO;
                } else {
                    isNight = true;
                    item.setTitle("白天模式");
                    nightMode = AppCompatDelegate.MODE_NIGHT_YES;
                }
                AppCompatDelegate.setDefaultNightMode(nightMode);
                if (Build.VERSION.SDK_INT >= 11) {
                    recreate();
                }
                return true;
            case R.id.navExit:
                finish();
                return true;
            default:
                return true;
        }
    }
}
