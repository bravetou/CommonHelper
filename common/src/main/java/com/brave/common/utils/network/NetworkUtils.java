package com.brave.common.utils.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.brave.common.CommonConfig;
import com.brave.common.utils.DeviceUtils;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/13 <br/>
 * <b>description</b> ： Network 相关工具类
 */
public final class NetworkUtils {
    //******************************************************************
    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    private static final int NETWORK_TYPE_IWLAN = 18;
    //******************************************************************

    private NetworkUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getInstance().getContext();
    }

    /**
     * 获取 ConnectivityManager 对象
     */
    public static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 获取活动网络信息
     */
    public static NetworkInfo getActiveNetworkInfo() {
        return getConnectivityManager().getActiveNetworkInfo();
    }

    /**
     * 打开网络设置界面<br/>
     * 3.0 以下打开设置界面
     */
    public static void openNetworkSettings() {
        Context context = getContext();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            context.startActivity(new Intent(Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    /**
     * 设置 移动数据（打开 或 关闭）
     */
    public static void setDataEnabled(boolean enable) {
        DeviceUtils.setDataEnabled(enable);
    }

    /**
     * 打开移动数据
     */
    public static void openDataEnabled() {
        DeviceUtils.openDataEnabled();
    }

    /**
     * 关闭移动数据
     */
    public static void closeDataEnabled() {
        DeviceUtils.closeDataEnabled();
    }

    /**
     * 移动数据 是否 打开
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDataEnabled() {
        return DeviceUtils.isDataEnabled();
    }

    /**
     * 设置 wifi（打开 或 关闭）
     *
     * @param enabled {@code true}: 打开<br>{@code false}: 关闭
     */
    public static void setWifiEnabled(boolean enabled) {
        DeviceUtils.setWifiEnabled(enabled);
    }

    /**
     * 开启 WIFI
     */
    public static void openWifiEnabled() {
        DeviceUtils.openWifiEnabled();
    }

    /**
     * 关闭 WIFI
     */
    public static void closeWifiEnabled() {
        DeviceUtils.closeWifiEnabled();
    }

    /**
     * 判断 wifi 是否 打开
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isWifiEnabled() {
        return DeviceUtils.isWifiEnabled();
    }

    /**
     * 判断网络是否可用
     *
     * @return {@code true}: 可用<br>{@code false}: 不可用
     */
    public static boolean isAvailable() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }

    /**
     * 判断网络是否连接
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isConnected() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    /**
     * 判断 wifi 是否连接
     *
     * @return {@code true}: 连接<br>{@code false}: 未连接
     */
    public static boolean isWifiConnected() {
        NetworkInfo info = getActiveNetworkInfo();
        return null != info && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 获取 当前 网络类型(WIFI,2G,3G,4G)
     *
     * @return 网络类型 {@link NetworkType}
     */
    public static NetworkType getNetworkType() {
        // 记录网络类型
        NetworkType networkType = NetworkType.NO;
        NetworkInfo info = getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                networkType = NetworkType.WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {
                    case NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        networkType = NetworkType.G2;
                        break;

                    case NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        networkType = NetworkType.G3;
                        break;

                    case NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        networkType = NetworkType.G4;
                        break;

                    default:
                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            networkType = NetworkType.G3;
                        } else {
                            networkType = NetworkType.UNKNOWN;
                        }
                        break;
                }
            } else {
                networkType = NetworkType.UNKNOWN;
            }
        }
        return networkType;
    }

    /**
     * 获取 当前 网络类型名(WIFI,2G,3G,4G)
     *
     * @return 网络类型 名称 {@link NetworkType#getValue()}
     */
    public static String getNetWorkTypeName() {
        return getNetworkType().getValue();
    }
}