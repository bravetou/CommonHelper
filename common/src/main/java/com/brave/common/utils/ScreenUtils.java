package com.brave.common.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;

import com.brave.common.CommonConfig;

import java.lang.reflect.Method;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ： 屏幕相关 工具类
 */
public final class ScreenUtils {
    // 获取屏幕休眠时长的错误码
    public final static int GET_SLEEP_TIME_ERROR = -200;

    private ScreenUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getInstance().getContext();
    }

    private static Resources getResources() {
        return getContext().getResources();
    }

    private static DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    /**
     * 获取屏幕宽度（单位：px）
     *
     * @return 宽
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度（单位：px）（含状态栏）
     *
     * @return 高
     */
    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕的宽度和高度（单位：px）（含状态栏）
     *
     * @return Point (X,Y) >>> 宽高
     */
    public static Point getScreenSize() {
        DisplayMetrics metrics = getDisplayMetrics();
        return new Point(metrics.widthPixels, metrics.heightPixels);
    }

    /**
     * 获取屏幕的物理尺寸（单位：英寸）
     *
     * @return 物理尺寸
     */
    public static float getScreenPhysicalSize() {
        DisplayMetrics metrics = getDisplayMetrics();
        float xdpi = metrics.xdpi;
        float ydpi = metrics.ydpi;
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        float w = (width / xdpi) * (width / xdpi);
        float h = (height / ydpi) * (width / xdpi);
        return (float) Math.sqrt(w + h);
    }

    /**
     * 获取屏幕 高、宽的比
     *
     * @return 屏幕高、宽的比
     */
    public static float getScreenRatio() {
        DisplayMetrics metrics = getDisplayMetrics();
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        return height / width;
    }

    /**
     * 获取屏幕密度（指每平方英寸中的像素数）<br/>
     * 例如：0.75 / 1 / 1.5 / ...（dpi/160 可得）
     *
     * @return 屏幕密度
     */
    public static float getScreenDensity() {
        return getDisplayMetrics().density;
    }

    /**
     * 获取屏幕像素密度 dpi （指每英寸中的像素数）<br/>
     * 例如：120 / 160 / 240 /...
     *
     * @return 像素密度 dpi
     */
    public static int getDensityDpi() {
        return getDisplayMetrics().densityDpi;
    }

    /**
     * 获取状态栏的高度
     *
     * @return 状态栏的高度
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取底部导航栏的高度
     *
     * @return 底部导航栏的高度
     */
    public static int getNavBarHeight() {
        int result = 0;
        // 判断底部导航栏是否显示
        int rid = getResources().getIdentifier(
                "config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            int resourceId = getResources().getIdentifier(
                    "navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 是否拥有底部导航栏
     *
     * @return {@code true}: 有底部导航栏<br>{@code false}: 反之
     */
    public static boolean hasNavigationBar() {
        boolean hasNavigationBar = false;
        int id = getResources().getIdentifier(
                "config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = getResources().getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if (navBarOverride.equals("1")) {
                hasNavigationBar = false;
            } else if (navBarOverride.equals("0")) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasNavigationBar;
    }

    /**
     * 设置指定 Activity 的屏幕为全屏<br/>
     * 需在 {@link Activity#setContentView(int)} 之前调用
     */
    public static void setFullScreen(@NonNull Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置屏幕为横屏
     * <p>还有一种就是在 Activity 中加属性 android:screenOrientation="landscape"</p>
     * <p>不设置 Activity 的 android:configChanges 时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次</p>
     * <p>设置 Activity 的 android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次</p>
     * <p>设置 Activity 的 android:configChanges="orientation|keyboardHidden|screenSize"（4.0 以上必须带最后一个参数）时
     * 切屏不会重新调用各个生命周期，只会执行 onConfigurationChanged 方法</p>
     */
    public static void setLandscape(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 设置屏幕为竖屏
     * <p>还有一种就是在 Activity 中加属性 android:screenOrientation="portrait"</p>
     * <p>不设置 Activity 的 android:configChanges 时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次</p>
     * <p>设置 Activity 的 android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次</p>
     * <p>设置 Activity 的 android:configChanges="orientation|keyboardHidden|screenSize"（4.0 以上必须带最后一个参数）时
     * 切屏不会重新调用各个生命周期，只会执行 onConfigurationChanged 方法</p>
     *
     * @param activity activity
     */
    public static void setPortrait(@NonNull final Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 是否显示为横屏
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 是否显示为竖屏
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isPortrait() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 获取屏幕旋转角度
     *
     * @return 屏幕旋转角度
     */
    public static int getScreenRotation(@NonNull Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            default:
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
    }

    /**
     * 是否为锁屏状态
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isLockScreen() {
        KeyguardManager km = (KeyguardManager) getContext().getSystemService(Context.KEYGUARD_SERVICE);
        return km.inKeyguardRestrictedInputMode();
    }

    /**
     * 设置进入休眠时长
     */
    public static void setSleepTime(int duration) {
        Settings.System.putInt(getContext().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, duration);
    }

    /**
     * 获取进入休眠时长
     *
     * @return 进入休眠时长，报错返回 {@link #GET_SLEEP_TIME_ERROR}
     */
    public static int getSleepTime() {
        try {
            return Settings.System.getInt(getContext().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return GET_SLEEP_TIME_ERROR;
        }
    }

    /**
     * 设备是否为平板手机
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isPhablet() {
        return (getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}