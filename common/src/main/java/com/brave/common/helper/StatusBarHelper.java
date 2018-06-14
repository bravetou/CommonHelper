package com.brave.common.helper;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.brave.common.utils.ColorUtils;
import com.brave.common.utils.ScreenUtils;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/14 <br/>
 * <b>description</b> ：状态栏（包含顶部和底部） 帮助类
 */
public class StatusBarHelper {
    private static final String STATUS_BAR_TAG = "STATUS_BAR_HELPER_BRAVE";

    private static class SingletonHolder {
        private volatile static StatusBarHelper mInstance = new StatusBarHelper();
    }

    public static StatusBarHelper getInstance() {
        return SingletonHolder.mInstance;
    }

    private StatusBarHelper() {
    }

    /**
     * 清除状态栏系统自带标记
     */
    public void clearStatusBarFlags(@NonNull Activity activity) {
        clearStatusBarFlags(activity.getWindow());
    }

    /**
     * 清除状态栏系统自带标记
     */
    public void clearStatusBarFlags(@NonNull Dialog dialog) {
        clearStatusBarFlags(dialog.getWindow());
    }

    /**
     * 清除状态栏系统自带标记
     */
    public void clearStatusBarFlags(@NonNull Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Android api 为21及其以上，需要设置及清除的标记
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            //Android api 为21以下，需要设置的标记
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 获取 StatusBar
     */
    private View getStatusBar(@NonNull Window window) {
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        View statusBar = decorView.findViewWithTag(STATUS_BAR_TAG);
        if (null == statusBar) {
            statusBar = new View(window.getContext());
            statusBar.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getStatusBarHeight()));
            statusBar.setTag(STATUS_BAR_TAG);
            decorView.addView(statusBar);
        }
        return statusBar;
    }

    /**
     * 设置状态栏颜色
     */
    public void setStatusBarColor(@NonNull Window window, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        } else {
            getStatusBar(window).setBackgroundColor(color);
        }
    }

    /**
     * 设置状态栏的透明度
     */
    public void setStatusBarAlpha(@NonNull Window window, @FloatRange(from = 0, to = 1) float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ColorUtils.argb(alpha, window.getStatusBarColor()));
        } else {
            View statusBar = getStatusBar(window);
            Drawable drawable = statusBar.getBackground();
            if (drawable instanceof ColorDrawable) {
                statusBar.setBackgroundColor(ColorUtils.argb(alpha, ((ColorDrawable) drawable).getColor()));
            }
        }
    }

    /**
     * 设置 Activity 的状态栏颜色
     *
     * @param color   颜色
     * @param isPerch 状态栏是否占位
     */
    public void setStatusBarColor(@NonNull Activity activity, @ColorInt int color, boolean isPerch) {
        clearStatusBarFlags(activity);
        if (isPerch) {
            ((ViewGroup) activity.findViewById(android.R.id.content))
                    .getChildAt(0)
                    .setFitsSystemWindows(true);
        }
        setStatusBarColor(activity.getWindow(), color);
    }

    /**
     * 设置 Activity 的状态栏颜色
     *
     * @param colorRes 颜色资源
     * @param isPerch  状态栏是否占位
     */
    public void setStatusBarColorRes(@NonNull Activity activity, @ColorRes int colorRes, boolean isPerch) {
        setStatusBarColor(activity, activity.getResources().getColor(colorRes), isPerch);
    }

    /**
     * 设置 Activity 的状态栏颜色
     *
     * @param color 颜色
     */
    public void setStatusBarColor(@NonNull Activity activity, @ColorInt int color) {
        setStatusBarColor(activity, color, true);
    }

    /**
     * 设置 Activity 的状态栏颜色
     *
     * @param colorRes 颜色资源
     */
    public void setStatusBarColorRes(@NonNull Activity activity, @ColorRes int colorRes) {
        setStatusBarColorRes(activity, colorRes, true);
    }

    /**
     * 设置状态栏的透明度
     */
    public void setStatusBarAlpha(@NonNull Activity activity, @FloatRange(from = 0, to = 1) float alpha) {
        setStatusBarAlpha(activity.getWindow(), alpha);
    }

    /**
     * 设置状态栏的透明度
     */
    public void setStatusBarAlpha(@NonNull Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        setStatusBarAlpha(activity, ((float) alpha / 255f));
    }

    /**
     * 设置 Dialog 的状态栏颜色
     *
     * @param color    颜色
     * @param rootView 状态栏占位（null 则为不占位）
     */
    public void setStatusBarColor(@NonNull Dialog dialog, @ColorInt int color, View rootView) {
        clearStatusBarFlags(dialog);
        if (null != rootView) {
            rootView.setFitsSystemWindows(true);
        }
        setStatusBarColor(dialog.getWindow(), color);
    }

    /**
     * 设置 Dialog 的状态栏颜色
     *
     * @param colorRes 颜色资源
     * @param rootView 状态栏占位（null 则为不占位）
     */
    public void setStatusBarColorRes(@NonNull Dialog dialog, @ColorRes int colorRes, View rootView) {
        setStatusBarColor(dialog, dialog.getContext().getResources().getColor(colorRes), rootView);
    }

    /**
     * 设置 Dialog 的状态栏颜色
     *
     * @param color 颜色
     */
    public void setStatusBarColor(@NonNull Dialog dialog, @ColorInt int color) {
        setStatusBarColor(dialog, color, null);
    }

    /**
     * 设置 Dialog 的状态栏颜色
     *
     * @param colorRes 颜色资源
     */
    public void setStatusBarColorRes(@NonNull Dialog dialog, @ColorRes int colorRes) {
        setStatusBarColorRes(dialog, colorRes, null);
    }

    /**
     * 设置状态栏的透明度
     */
    public void setStatusBarAlpha(@NonNull Dialog dialog, @FloatRange(from = 0, to = 1) float alpha) {
        setStatusBarAlpha(dialog.getWindow(), alpha);
    }

    /**
     * 设置状态栏的透明度
     */
    public void setStatusBarAlpha(@NonNull Dialog dialog, @IntRange(from = 0, to = 255) int alpha) {
        setStatusBarAlpha(dialog.getWindow(), ((float) alpha / 255f));
    }

    /**
     * 设置状态栏一体化
     */
    public void setIntegration(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        clearStatusBarFlags(activity);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        if (viewGroup.getChildCount() < 1) {
            return;
        }
        View child = viewGroup.getChildAt(0);
        if (child.getFitsSystemWindows()) {
            child.setFitsSystemWindows(false);
        }
        if (child instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) child;
            if (group.getChildCount() < 1) {
                return;
            }
            View groupChild = group.getChildAt(0);
            if (groupChild instanceof ViewGroup) {
                ViewGroup topBars = (ViewGroup) groupChild;
                topBars.setFitsSystemWindows(true);
            } else {
                group.removeView(groupChild);

                Drawable drawable = groupChild.getBackground();

                RelativeLayout layout = new RelativeLayout(activity);
                layout.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.setBackground(drawable);
                layout.addView(groupChild);
                layout.setFitsSystemWindows(true);

                group.addView(layout, 0);
            }
        }
    }
}