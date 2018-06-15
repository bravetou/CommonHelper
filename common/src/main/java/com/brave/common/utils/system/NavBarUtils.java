package com.brave.common.utils.system;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/15 <br/>
 * <b>description</b> ：导航栏 工具类
 */
public final class NavBarUtils {
    public NavBarUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    public static void clearFlags(@NonNull Window window) {
        SystemBarUtils.clearSystemFlags(window, false, true);
    }

    public static void clearFlags(@NonNull Activity activity) {
        clearFlags(activity.getWindow());
    }

    public static void clearFlags(@NonNull Dialog dialog) {
        clearFlags(dialog.getWindow());
    }

    public static void clear(@NonNull Window window) {
        SystemBarUtils.clearNavBar(window);
    }

    public static void clear(@NonNull Activity activity) {
        clear(activity.getWindow());
    }

    public static void clear(@NonNull Dialog dialog) {
        clear(dialog.getWindow());
    }

    public static void setColor(@NonNull Activity activity, @ColorInt int color) {
        SystemBarUtils.setNavBarColor(activity, color);
    }

    public static void setColorRes(@NonNull Activity activity, @ColorRes int colorRes) {
        SystemBarUtils.setNavBarColorRes(activity, colorRes);
    }

    public static void setColor(@NonNull Dialog dialog, @ColorInt int color, View rootView) {
        SystemBarUtils.setNavBarColor(dialog, color, rootView);
    }

    public static void setColorRes(@NonNull Dialog dialog, @ColorRes int colorRes, View rootView) {
        SystemBarUtils.setNavBarColorRes(dialog, colorRes, rootView);
    }

    public static void setAlpha(@NonNull Dialog dialog, @IntRange(from = 0, to = 255) int alpha) {
        SystemBarUtils.setNavBarAlpha(dialog, alpha);
    }

    public static void setAlpha(@NonNull Dialog dialog, @FloatRange(from = 0, to = 1) float alpha) {
        SystemBarUtils.setNavBarAlpha(dialog, alpha);
    }

    public static void setAlpha(@NonNull Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        SystemBarUtils.setNavBarAlpha(activity, alpha);
    }

    public static void setAlpha(@NonNull Activity activity, @FloatRange(from = 0, to = 1) float alpha) {
        SystemBarUtils.setNavBarAlpha(activity, alpha);
    }
}