package com.brave.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ： APP 工具类
 */
public class AppUtils {
    private Context mContext;

    private static class SingletonHolder {
        private volatile static AppUtils mInstance = new AppUtils();
    }

    public static AppUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private AppUtils() {
        mContext = CommonConfig.getInstance().getContext();
    }

    /*public AppUtils with(Context mContext) {
        this.mContext = mContext;
        return getInstance();
    }*/

    /**
     * 获取 PackageInfo 对象
     */
    public PackageInfo getPackageInfo() {
        try {
            PackageManager packageManager = mContext.getPackageManager();
            return packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 APP 名称
     */
    public String getAppName() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? null : mContext.getResources().getString(
                packageInfo.applicationInfo.labelRes);
    }

    /**
     * 获取 APP 版本名
     */
    public String getVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? null : packageInfo.versionName;
    }

    /**
     * 获取 APP 版本号
     */
    public int getVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? -1 : packageInfo.versionCode;
    }

    /**
     * 获取 设备厂商
     */
    public String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取 设备名称
     */
    public String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取 PackageManager 对象
     */
    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 获取 设备ID(DeviceId)
     */
    @SuppressLint("MissingPermission")
    public String getDeviceId() {
        return getTelephonyManager().getDeviceId();
    }

    /**
     * 获取 设备SIM(SimSerialNumber)
     */
    @SuppressLint("MissingPermission")
    public String getSimSerialNumber() {
        return getTelephonyManager().getSimSerialNumber();
    }

    /**
     * 获取 设备IMSI(SubscriberId)
     */
    @SuppressLint("MissingPermission")
    public String getSubscriberId() {
        return getTelephonyManager().getSubscriberId();
    }
}