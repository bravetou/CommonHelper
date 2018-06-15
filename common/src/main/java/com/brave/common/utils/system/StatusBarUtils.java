package com.brave.common.utils.system;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.RelativeLayout;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/14 <br/>
 * <b>description</b> ：状态栏 工具类
 */
public final class StatusBarUtils {
    private StatusBarUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    public static void clearFlags(@NonNull Window window) {
        SystemBarUtils.clearSystemFlags(window, true, false);
    }

    public static void clearFlags(@NonNull Activity activity) {
        clearFlags(activity.getWindow());
    }

    public static void clearFlags(@NonNull Dialog dialog) {
        clearFlags(dialog.getWindow());
    }

    public static void clear(@NonNull Window window) {
        SystemBarUtils.clearStatusBar(window);
    }

    public static void clear(@NonNull Activity activity) {
        clear(activity.getWindow());
    }

    public static void clear(@NonNull Dialog dialog) {
        clear(dialog.getWindow());
    }

    public static void setColor(@NonNull Activity activity, @ColorInt int color) {
        SystemBarUtils.setStatusBarColor(activity, color);
    }

    public static void setColorRes(@NonNull Activity activity, @ColorRes int colorRes) {
        SystemBarUtils.setStatusBarColorRes(activity, colorRes);
    }

    public static void setColor(@NonNull Dialog dialog, @ColorInt int color, View rootView) {
        SystemBarUtils.setStatusBarColor(dialog, color, rootView);
    }

    public static void setColorRes(@NonNull Dialog dialog, @ColorRes int colorRes, View rootView) {
        SystemBarUtils.setStatusBarColorRes(dialog, colorRes, rootView);
    }

    public static void setAlpha(@NonNull Dialog dialog, @IntRange(from = 0, to = 255) int alpha) {
        SystemBarUtils.setStatusBarAlpha(dialog, alpha);
    }

    public static void setAlpha(@NonNull Dialog dialog, @FloatRange(from = 0, to = 1) float alpha) {
        SystemBarUtils.setStatusBarAlpha(dialog, alpha);
    }

    public static void setAlpha(@NonNull Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        SystemBarUtils.setStatusBarAlpha(activity, alpha);
    }

    public static void setAlpha(@NonNull Activity activity, @FloatRange(from = 0, to = 1) float alpha) {
        SystemBarUtils.setStatusBarAlpha(activity, alpha);
    }

    /**
     * 设置状态栏一体化
     */
    public static void setIntegration(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        clearFlags(activity);
        // NavBarUtils.clearFlags(activity.getWindow());
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
                groupChild.setFitsSystemWindows(true);
            } else {
                RelativeLayout layout = new RelativeLayout(activity);
                layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Drawable drawable = groupChild.getBackground();
                if (drawable instanceof ColorDrawable) {
                    ColorDrawable colorDrawable = (ColorDrawable) drawable;
                    layout.setBackgroundColor(colorDrawable.getColor());
                } else {
                    layout.setBackground(drawable);
                }
                layout.setFitsSystemWindows(true);

                group.removeView(groupChild);

                layout.addView(groupChild);

                group.addView(layout, 0);
            }
        }
    }

    /**
     * 设置状态栏一体化
     */
    public static void setIntegration(@NonNull Dialog dialog, View rootView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        clearFlags(dialog);
        if (rootView instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) rootView;
            if (group.getChildCount() < 1) {
                return;
            }
            View groupChild = group.getChildAt(0);
            if (groupChild instanceof ViewGroup) {
                groupChild.setFitsSystemWindows(true);
            } else {
                RelativeLayout layout = new RelativeLayout(dialog.getContext());
                layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Drawable drawable = groupChild.getBackground();
                if (drawable instanceof ColorDrawable) {
                    ColorDrawable colorDrawable = (ColorDrawable) drawable;
                    layout.setBackgroundColor(colorDrawable.getColor());
                } else {
                    layout.setBackground(drawable);
                }
                layout.setFitsSystemWindows(true);

                group.removeView(groupChild);

                layout.addView(groupChild);

                group.addView(layout, 0);
            }
        }
    }
}