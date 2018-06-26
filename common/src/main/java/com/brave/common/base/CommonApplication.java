package com.brave.common.base;

import android.support.multidex.MultiDexApplication;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：继承 Google 官方 MultiDex 包下MultiDexApplication，防止64（65）K 问题
 */
public class CommonApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // 个人常用配置
        CommonConfig.init(this);
    }
}