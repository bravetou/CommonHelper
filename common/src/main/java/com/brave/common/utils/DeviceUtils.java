package com.brave.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ：安卓 设备相关工具类
 */
public final class DeviceUtils {
    private DeviceUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getInstance().getContext();
    }

    /**
     * 获取 设备厂商
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取 设备名称
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取 PackageManager 对象
     */
    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 获取 设备ID(DeviceId)
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId() {
        return getTelephonyManager().getDeviceId();
    }

    /**
     * 获取 设备SIM(SimSerialNumber)
     */
    @SuppressLint("MissingPermission")
    public static String getSimSerialNumber() {
        return getTelephonyManager().getSimSerialNumber();
    }

    /**
     * 获取 设备IMSI(SubscriberId)
     */
    @SuppressLint("MissingPermission")
    public static String getSubscriberId() {
        return getTelephonyManager().getSubscriberId();
    }
}