package com.airxiao.o.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.airxiao.o.R;

/**
 * Created by xiaoyunlou on 17/12/13.
 */

public class ProjectActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, ProjectActivity.class);
        mContext.startActivity(intent);
    }
}
