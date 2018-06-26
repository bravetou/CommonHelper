package com.brave.common.utils;

import android.content.Context;
import android.content.Intent;
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

    /**
     * 跳转到系统设置界面
     */
    public static void openSettings() {
        final Intent intent = new Intent(Settings.ACTION_SETTINGS);
        getContext().startActivity(intent);
    }

    /**
     * 跳转到 Wifi 列表设置界面
     */
    public static void openWifiSettings() {
        final Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        getContext().startActivity(intent);
    }

    /**
     * 跳转到移动网络设置界面
     */
    public static void openDataSettings() {
        final Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        getContext().startActivity(intent);
    }

    /**
     * 跳转到飞行模式设置界面
     */
    public static void openAirplaneSettings() {
        final Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        getContext().startActivity(intent);
    }

    /**
     * 跳转到蓝牙设置界面
     */
    public static void openBluetoothSettings() {
        final Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        getContext().startActivity(intent);
    }

    /**
     * 跳转到 NFC 设置界面
     */
    public static void openNfcSettings() {
        final Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
        getContext().startActivity(intent);
    }

    /**
     * 跳转到 NFC 共享设置界面
     */
    public static void openNfcShareSettings() {
        final Intent intent = new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
        getContext().startActivity(intent);
    }

    /**
     * 跳转位置服务界面
     */
    public static void openGpsSettings() {
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        getContext().startActivity(intent);
    }
}