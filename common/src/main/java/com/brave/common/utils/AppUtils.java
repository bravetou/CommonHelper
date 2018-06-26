package com.brave.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ： APP 工具类
 */
public final class AppUtils {
    private AppUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getContext();
    }

    /**
     * 获取 PackageInfo 对象
     */
    public static PackageInfo getPackageInfo() {
        try {
            PackageManager packageManager = getContext().getPackageManager();
            return packageManager.getPackageInfo(
                    getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 APP 名称
     */
    public static String getAppName() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? null : getContext().getResources().getString(
                packageInfo.applicationInfo.labelRes);
    }

    /**
     * 获取 APP 版本名
     */
    public static String getVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? null : packageInfo.versionName;
    }

    /**
     * 获取 APP 版本号
     */
    public static int getVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? -1 : packageInfo.versionCode;
    }
}