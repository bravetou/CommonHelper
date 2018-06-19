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
 * <b>description</b> ：导航栏 工具类<br/><b>注意：</b><ul>
 * <li>没有对状态栏做操作：隐藏导航栏时，需要状态栏会覆盖布局</li>
 * <li>对状态栏进行了操作：隐藏状态栏时，顶部会出现白条（statusHeight）</li>
 * </ul>
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

    public static void hide(@NonNull Window window) {
        SystemBarUtils.hideNavBar(window);
    }

    public static void hide(@NonNull Activity activity) {
        SystemBarUtils.hideNavBar(activity);
    }

    public static void hide(@NonNull Dialog dialog) {
        SystemBarUtils.hideNavBar(dialog);
    }

    public static void show(@NonNull Window window) {
        SystemBarUtils.hideStatusBar(window);
    }

    public static void show(@NonNull Activity activity) {
        SystemBarUtils.hideStatusBar(activity);
    }

    public static void show(@NonNull Dialog dialog) {
        SystemBarUtils.hideStatusBar(dialog);
    }
}