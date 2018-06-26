package com.brave.common.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.brave.common.CommonConfig;
import com.brave.common.utils.network.NetworkUtils;
import com.brave.common.utils.permission.PermissionsUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        return CommonConfig.getContext();
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
     * 获取 TelephonyManager 对象
     */
    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 获取 WifiManager 对象
     */
    @SuppressLint("WifiManagerLeak")
    public static WifiManager getWifiManager() {
        return (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
    }

    /**
     * 是否拥有读取手机信息权限
     */
    private static boolean hasReadPhoneStatePermission() {
        return PermissionsUtils.hasPermissions(Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * 是否拥有修改手机信息权限
     */
    private static boolean hasModifyPhoneStatePermission() {
        return PermissionsUtils.hasPermissions(Manifest.permission.MODIFY_PHONE_STATE);
    }

    /**
     * 获取 设备ID(DeviceId)
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId() {
        if (!hasReadPhoneStatePermission()) {
            return "";
        }
        return getTelephonyManager().getDeviceId();
    }

    /**
     * 获取 设备SIM(SimSerialNumber)
     */
    @SuppressLint("MissingPermission")
    public static String getSimSerialNumber() {
        if (!hasReadPhoneStatePermission()) {
            return "";
        }
        return getTelephonyManager().getSimSerialNumber();
    }

    /**
     * 获取 设备IMSI(SubscriberId)
     */
    @SuppressLint("MissingPermission")
    public static String getSubscriberId() {
        if (!hasReadPhoneStatePermission()) {
            return "";
        }
        return getTelephonyManager().getSubscriberId();
    }

    /**
     * 获取 网络运营商 名称<br/>
     * 如 ：中国联通、中国移动、中国电信
     */
    public static String getNetworkOperatorName() {
        String networkOperatorName = getTelephonyManager().getNetworkOperatorName();
        return null == networkOperatorName ? "" : networkOperatorName;
    }

    /**
     * 获取 移动终端 类型
     *
     * @return 手机制式
     * <ul>
     * <li>{@link TelephonyManager#PHONE_TYPE_NONE } : 0 手机制式未知</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_GSM  } : 1 手机制式为GSM，移动和联通</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_CDMA } : 2 手机制式为CDMA，电信</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_SIP  } : 3</li>
     * </ul>
     */
    public static int getPhoneType() {
        return getTelephonyManager().getPhoneType();
    }

    /**
     * 设置 移动数据（打开 或 关闭）
     */
    public static void setDataEnabled(boolean enable) {
        if (!hasModifyPhoneStatePermission()) {
            // 如果没有修改网络权限，则打开网络设置界面
            NetworkUtils.openNetworkSettings();
            return;
        }
        TelephonyManager manager = getTelephonyManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.setDataEnabled(enable);
        } else {
            try {
                Method setMobileDataEnabledMethod = manager.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
                if (null != setMobileDataEnabledMethod) {
                    setMobileDataEnabledMethod.invoke(manager, enable);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打开移动数据
     */
    public static void openDataEnabled() {
        setDataEnabled(true);
    }

    /**
     * 关闭移动数据
     */
    public static void closeDataEnabled() {
        setDataEnabled(false);
    }

    /**
     * 移动数据 是否 打开
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDataEnabled() {
        boolean flag = false;
        TelephonyManager manager = getTelephonyManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            flag = manager.isDataEnabled();
        } else {
            try {
                Method getMobileDataEnabledMethod = manager.getClass().getDeclaredMethod("isDataEnabled");
                if (getMobileDataEnabledMethod != null) {
                    flag = (boolean) getMobileDataEnabledMethod.invoke(manager);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 设置 wifi（打开 或 关闭）
     *
     * @param enabled {@code true}: 打开<br>{@code false}: 关闭
     */
    public static void setWifiEnabled(boolean enabled) {
        WifiManager manager = getWifiManager();
        if (enabled) {
            if (!manager.isWifiEnabled()) {
                manager.setWifiEnabled(true);
            }
        } else {
            if (manager.isWifiEnabled()) {
                manager.setWifiEnabled(false);
            }
        }
    }

    /**
     * 开启 WIFI
     */
    public static void openWifiEnabled() {
        setWifiEnabled(true);
    }

    /**
     * 关闭 WIFI
     */
    public static void closeWifiEnabled() {
        setWifiEnabled(false);
    }

    /**
     * 判断 wifi 是否 打开
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isWifiEnabled() {
        return getWifiManager().isWifiEnabled();
    }
}