package com.brave.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/13 <br/>
 * <b>description</b> ： 进入 指定系统功能界面 相关工具类
 */
public final class SettingsUtils {
    private SettingsUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getContext();
    }

    private static final Intent getIntent() {
        return new Intent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 跳转到系统设置界面
     */
    public static void openSettings() {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_SETTINGS));
    }

    /**
     * 跳转到 Wifi 列表设置界面
     */
    public static void openWifiSettings() {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_WIFI_SETTINGS));
    }

    /**
     * 跳转到移动网络设置界面
     */
    public static void openDataSettings() {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_DATA_ROAMING_SETTINGS));
    }

    /**
     * 跳转到飞行模式设置界面
     */
    public static void openAirplaneSettings() {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
    }

    /**
     * 跳转到蓝牙设置界面
     */
    public static void openBluetoothSettings() {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_BLUETOOTH_SETTINGS));
    }

    /**
     * 跳转到 NFC 设置界面
     */
    public static void openNfcSettings() {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_NFC_SETTINGS));
    }

    /**
     * 跳转到 NFC 共享设置界面
     */
    public static void openNfcShareSettings() {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_NFCSHARING_SETTINGS));
    }

    /**
     * 跳转位置服务界面
     */
    public static void openGpsSettings() {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    /**
     * 跳转到指定应用包下的详情界面
     */
    public static void openAppDetails(String packageName) {
        getContext().startActivity(getIntent()
                .setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .setData(Uri.fromParts("package", packageName, null)));
    }

    /**
     * 跳转到本应用包下的详情界面
     */
    public static void openAppDetails() {
        openAppDetails(CommonConfig.getApplication().getPackageName());
    }
}