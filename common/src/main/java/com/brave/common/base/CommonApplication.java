package com.brave.common.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：继承 Google 官方 MultiDex 包下MultiDexApplication，防止64（65）K 问题
 */
public class CommonApplication extends MultiDexApplication {
    private static volatile CommonApplication mInstance;

    public static Application getApplication() {
        return mInstance;
    }

    public static CommonApplication getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}