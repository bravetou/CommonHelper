package com.brave.employ.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.brave.common.base.v4.CommonActivity;
import com.brave.employ.R;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：主页
 */
public class HomeActivity extends CommonActivity {
    @Override
    protected int loadLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}