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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.brave.common.utils.ColorUtils;
import com.brave.common.utils.ScreenUtils;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/15 <br/>
 * <b>description</b> ：系统 状态栏帮助类
 */
public final class SystemBarUtils {
    private static final String STATUS_BAR_TAG = "STATUS_BAR_HELPER_BRAVE";
    private static final String NAV_BAR_TAG = "NAV_BAR_HELPER_BRAVE";

    private SystemBarUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    /**
     * 清除 系统 标记
     *
     * @param isStatusBar 是否清除状态栏
     * @param isNavBar    是否清除导航栏
     */
    public static void clearSystemFlags(@NonNull Window window, boolean isStatusBar, boolean isNavBar) {
        if (!isStatusBar && !isNavBar) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // draw
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // status
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); // navigation
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity 顶端布局部分会被状态遮住
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE; // 防止系统栏隐藏时内容区域大小发生变化
            if (isNavBar) {
                option = option | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION; // 隐藏导航栏
            }
            window.getDecorView().setSystemUiVisibility(option);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isStatusBar) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            if (isNavBar) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }

    /**
     * 获取（创建）系统 状态栏、导航栏
     *
     * @param isStatusBar 是否为状态栏
     */
    private static View getSystemBar(@NonNull Window window, boolean isStatusBar) {
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        String tag = isStatusBar ? STATUS_BAR_TAG : NAV_BAR_TAG; //
        View systemBar = decorView.findViewWithTag(tag);
        if (null == systemBar) {
            systemBar = new View(window.getContext());
            int systemBarHeight = isStatusBar ? ScreenUtils.getStatusBarHeight() : ScreenUtils.getNavBarHeight();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, systemBarHeight);
            params.gravity = Gravity.TOP;
            systemBar.setLayoutParams(params);
            systemBar.setTag(tag);
            decorView.addView(systemBar);
        }
        return systemBar;
    }

    /**
     * 获取 StatusBar
     */
    private static View getStatusBar(@NonNull Window window) {
        return getSystemBar(window, true);
    }

    /**
     * 获取 NavBar
     */
    private static View getNavBar(@NonNull Window window) {
        return getSystemBar(window, false);
    }

    /**
     * 清除 系统 状态栏、导航栏
     *
     * @param isStatus 是否清除状态栏
     * @param isNav    是否清除导航栏
     */
    private static void clearSystemBar(@NonNull Window window, boolean isStatus, boolean isNav) {
        if (!isStatus && !isNav) {
            return;
        }
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        if (isStatus) {
            View statusBar = decorView.findViewWithTag(STATUS_BAR_TAG);
            if (null != statusBar) {
                decorView.removeView(statusBar);
            }
        }
        if (isNav) {
            View navBar = decorView.findViewWithTag(NAV_BAR_TAG);
            if (null != navBar) {
                decorView.removeView(navBar);
            }
        }
    }

    /**
     * 清除 系统 状态栏、导航栏
     */
    public static void clearSystemBar(@NonNull Window window) {
        clearSystemBar(window, true, true);
    }

    /**
     * 清除 状态栏
     */
    public static void clearStatusBar(@NonNull Window window) {
        clearSystemBar(window, true, false);
    }

    /**
     * 清除 导航栏
     */
    public static void clearNavBar(@NonNull Window window) {
        clearSystemBar(window, false, true);
    }

    /**
     * 设置 系统栏 颜色（包含 状态栏、导航栏）
     *
     * @param color    颜色
     * @param rootView 系统栏占位（null 则为不占位）
     */
    private static void setSystemBarColor(@NonNull Window window, boolean isStatusBar, boolean isNavBar,
                                          @ColorInt int color, View rootView) {
        clearSystemFlags(window, isStatusBar, isNavBar);
        if (null != rootView) {
            if (rootView instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) rootView;
                viewGroup.setFitsSystemWindows(true);
                viewGroup.setClipToPadding(true);
            }
        }
        if (isStatusBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(color);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getStatusBar(window).setBackgroundColor(color);
            }
        }
        if (isNavBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setNavigationBarColor(color);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getNavBar(window).setBackgroundColor(color);
            }
        }
    }

    /**
     * 设置 系统栏 颜色（包含 状态栏、导航栏）
     *
     * @param color    颜色
     * @param rootView 系统栏占位（null 则为不占位）
     */
    public static void setSystemBarColor(@NonNull Window window, @ColorInt int color, View rootView) {
        setSystemBarColor(window, true, true, color, rootView);
    }

    /**
     * 设置 顶部状态栏 颜色
     *
     * @param color 颜色
     */
    public static void setStatusBarColor(@NonNull Window window, @ColorInt int color, View rootView) {
        setSystemBarColor(window, true, false, color, rootView);
    }

    /**
     * 设置 底部导航栏 颜色
     *
     * @param color 颜色
     */
    public static void setNavBarColor(@NonNull Window window, @ColorInt int color, View rootView) {
        setSystemBarColor(window, false, true, color, rootView);
    }

    /**
     * 设置 系统栏 颜色（包含 状态栏、导航栏）
     *
     * @param color    颜色资源
     * @param rootView 系统栏占位（null 则为不占位）
     */
    public static void setSystemBarColorRes(@NonNull Window window, @ColorRes int color, View rootView) {
        setSystemBarColor(window, window.getContext().getResources().getColor(color), rootView);
    }

    /**
     * 设置 顶部状态栏 颜色
     *
     * @param color 颜色资源
     */
    public static void setStatusBarColorRes(@NonNull Window window, @ColorRes int color, View rootView) {
        setStatusBarColor(window, window.getContext().getResources().getColor(color), rootView);
    }

    /**
     * 设置 底部导航栏 颜色
     *
     * @param color 颜色资源
     */
    public static void setNavBarColorRes(@NonNull Window window, @ColorRes int color, View rootView) {
        setNavBarColor(window, window.getContext().getResources().getColor(color), rootView);
    }

    /**
     * 设置 系统栏 颜色（包含 状态栏、导航栏）
     *
     * @param color 颜色
     */
    public static void setSystemBarColor(@NonNull Activity activity, @ColorInt int color) {
        ViewGroup group = activity.findViewById(android.R.id.content);
        if (group.getChildCount() < 1) {
            return;
        }
        setSystemBarColor(activity.getWindow(), color, group.getChildAt(0));
    }

    /**
     * 设置 顶部状态栏 颜色
     *
     * @param color 颜色
     */
    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int color) {
        ViewGroup group = activity.findViewById(android.R.id.content);
        if (group.getChildCount() < 1) {
            return;
        }
        setStatusBarColor(activity.getWindow(), color, group.getChildAt(0));
    }

    /**
     * 设置 底部导航栏 颜色
     *
     * @param color 颜色
     */
    public static void setNavBarColor(@NonNull Activity activity, @ColorInt int color) {
        ViewGroup group = activity.findViewById(android.R.id.content);
        if (group.getChildCount() < 1) {
            return;
        }
        setNavBarColor(activity.getWindow(), color, group.getChildAt(0));
    }

    /**
     * 设置 系统栏 颜色（包含 状态栏、导航栏）
     *
     * @param color 颜色资源
     */
    public static void setSystemBarColorRes(@NonNull Activity activity, @ColorRes int color) {
        setSystemBarColor(activity, activity.getResources().getColor(color));
    }

    /**
     * 设置 顶部状态栏 颜色
     *
     * @param color 颜色资源
     */
    public static void setStatusBarColorRes(@NonNull Activity activity, @ColorRes int color) {
        setStatusBarColor(activity, activity.getResources().getColor(color));
    }

    /**
     * 设置 底部导航栏 颜色
     *
     * @param color 颜色资源
     */
    public static void setNavBarColorRes(@NonNull Activity activity, @ColorRes int color) {
        setNavBarColor(activity, activity.getResources().getColor(color));
    }

    /**
     * 设置 系统栏 颜色（包含 状态栏、导航栏）
     *
     * @param color 颜色
     */
    public static void setSystemBarColor(@NonNull Dialog dialog, @ColorInt int color, View rootView) {
        setSystemBarColor(dialog.getWindow(), color, rootView);
    }

    /**
     * 设置 顶部状态栏 颜色
     *
     * @param color 颜色
     */
    public static void setStatusBarColor(@NonNull Dialog dialog, @ColorInt int color, View rootView) {
        setStatusBarColor(dialog.getWindow(), color, rootView);
    }

    /**
     * 设置 底部导航栏 颜色
     *
     * @param color 颜色
     */
    public static void setNavBarColor(@NonNull Dialog dialog, @ColorInt int color, View rootView) {
        setNavBarColor(dialog.getWindow(), color, rootView);
    }

    /**
     * 设置 系统栏 颜色（包含 状态栏、导航栏）
     *
     * @param color 颜色资源
     */
    public static void setSystemBarColorRes(@NonNull Dialog dialog, @ColorRes int color, View rootView) {
        setSystemBarColor(dialog.getWindow(), dialog.getContext().getResources().getColor(color), rootView);
    }

    /**
     * 设置 顶部状态栏 颜色
     *
     * @param color 颜色资源
     */
    public static void setStatusBarColorRes(@NonNull Dialog dialog, @ColorRes int color, View rootView) {
        setStatusBarColor(dialog.getWindow(), dialog.getContext().getResources().getColor(color), rootView);
    }

    /**
     * 设置 底部导航栏 颜色
     *
     * @param color 颜色资源
     */
    public static void setNavBarColorRes(@NonNull Dialog dialog, @ColorRes int color, View rootView) {
        setNavBarColor(dialog.getWindow(), dialog.getContext().getResources().getColor(color), rootView);
    }

    /**
     * 设置 系统栏 透明度
     *
     * @param alpha    透明度[0,1]
     * @param isStatus 是否调整状态栏
     * @param isNav    是否调整导航栏
     */
    private static void setSystemBarAlpha(@NonNull Window window, @FloatRange(from = 0, to = 1) float alpha, boolean isStatus, boolean isNav) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isStatus) {
                window.setStatusBarColor(ColorUtils.argb(alpha, window.getStatusBarColor()));
            }
            if (isNav) {
                window.setNavigationBarColor(ColorUtils.argb(alpha, window.getNavigationBarColor()));
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isStatus) {
                View statusBar = getStatusBar(window);
                Drawable drawable = statusBar.getBackground();
                if (drawable instanceof ColorDrawable) {
                    statusBar.setBackgroundColor(ColorUtils.argb(alpha, ((ColorDrawable) drawable).getColor()));
                }
            }
            if (isNav) {
                View navBar = getNavBar(window);
                Drawable drawable = navBar.getBackground();
                if (drawable instanceof ColorDrawable) {
                    navBar.setBackgroundColor(ColorUtils.argb(alpha, ((ColorDrawable) drawable).getColor()));
                }
            }
        }
    }

    /**
     * 设置 系统栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setSystemBarAlpha(@NonNull Window window, @FloatRange(from = 0, to = 1) float alpha) {
        setSystemBarAlpha(window, alpha, true, true);
    }

    /**
     * 设置 状态栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setStatusBarAlpha(@NonNull Window window, @FloatRange(from = 0, to = 1) float alpha) {
        setSystemBarAlpha(window, alpha, true, false);
    }

    /**
     * 设置 导航栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setNavBarAlpha(@NonNull Window window, @FloatRange(from = 0, to = 1) float alpha) {
        setSystemBarAlpha(window, alpha, false, true);
    }

    /**
     * 设置 系统栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setSystemBarAlpha(@NonNull Window window, @IntRange(from = 0, to = 255) int alpha) {
        setSystemBarAlpha(window, ((float) alpha / 255f));
    }

    /**
     * 设置 状态栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setStatusBarAlpha(@NonNull Window window, @IntRange(from = 0, to = 255) int alpha) {
        setStatusBarAlpha(window, ((float) alpha / 255f));
    }

    /**
     * 设置 导航栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setNavBarAlpha(@NonNull Window window, @IntRange(from = 0, to = 255) int alpha) {
        setNavBarAlpha(window, ((float) alpha / 255f));
    }

    /**
     * 设置 系统栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setSystemBarAlpha(@NonNull Activity activity, @FloatRange(from = 0, to = 1) float alpha) {
        setSystemBarAlpha(activity.getWindow(), alpha);
    }

    /**
     * 设置 状态栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setStatusBarAlpha(@NonNull Activity activity, @FloatRange(from = 0, to = 1) float alpha) {
        setStatusBarAlpha(activity.getWindow(), alpha);
    }

    /**
     * 设置 导航栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setNavBarAlpha(@NonNull Activity activity, @FloatRange(from = 0, to = 1) float alpha) {
        setNavBarAlpha(activity.getWindow(), alpha);
    }

    /**
     * 设置 系统栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setSystemBarAlpha(@NonNull Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        setSystemBarAlpha(activity, ((float) alpha / 255f));
    }

    /**
     * 设置 状态栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setStatusBarAlpha(@NonNull Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        setStatusBarAlpha(activity, ((float) alpha / 255f));
    }

    /**
     * 设置 导航栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setNavBarAlpha(@NonNull Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        setNavBarAlpha(activity, ((float) alpha / 255f));
    }

    /**
     * 设置 系统栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setSystemBarAlpha(@NonNull Dialog dialog, @FloatRange(from = 0, to = 1) float alpha) {
        setSystemBarAlpha(dialog.getWindow(), alpha);
    }

    /**
     * 设置 状态栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setStatusBarAlpha(@NonNull Dialog dialog, @FloatRange(from = 0, to = 1) float alpha) {
        setStatusBarAlpha(dialog.getWindow(), alpha);
    }

    /**
     * 设置 导航栏 透明度
     *
     * @param alpha 透明度[0,1]
     */
    public static void setNavBarAlpha(@NonNull Dialog dialog, @FloatRange(from = 0, to = 1) float alpha) {
        setNavBarAlpha(dialog.getWindow(), alpha);
    }

    /**
     * 设置 系统栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setSystemBarAlpha(@NonNull Dialog dialog, @IntRange(from = 0, to = 255) int alpha) {
        setSystemBarAlpha(dialog, ((float) alpha / 255f));
    }

    /**
     * 设置 状态栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setStatusBarAlpha(@NonNull Dialog dialog, @IntRange(from = 0, to = 255) int alpha) {
        setStatusBarAlpha(dialog, ((float) alpha / 255f));
    }

    /**
     * 设置 导航栏 透明度
     *
     * @param alpha 透明度[0,255]
     */
    public static void setNavBarAlpha(@NonNull Dialog dialog, @IntRange(from = 0, to = 255) int alpha) {
        setNavBarAlpha(dialog, ((float) alpha / 255f));
    }

    /**
     * 隐藏 状态栏、导航栏
     * <ul>
     * <li>{@link View#SYSTEM_UI_FLAG_FULLSCREEN} >>> View已请求进入正常的全屏模式，以便其内容可以接管屏幕，同时仍允许用户与应用程序进行交互</li>
     * <li>{@link View#SYSTEM_UI_FLAG_HIDE_NAVIGATION} >>> View已请求暂时隐藏系统导航</li>
     * <li>{@link View#SYSTEM_UI_FLAG_IMMERSIVE} >>> View隐藏导航栏时仍希望保持交互SYSTEM_UI_FLAG_HIDE_NAVIGATION</li>
     * <li>{@link View#SYSTEM_UI_FLAG_IMMERSIVE_STICKY} >>> 当隐藏状态栏SYSTEM_UI_FLAG_FULLSCREEN和/或隐藏导航栏时，View会保持交互SYSTEM_UI_FLAG_HIDE_NAVIGATION</li>
     * <li>{@link View#SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN} >>> View会将其窗口布置为好像它已经请求一样 SYSTEM_UI_FLAG_FULLSCREEN，即使它现在还没有</li>
     * <li>{@link View#SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION} >>> View会将其窗口布置为好像它已经请求一样 SYSTEM_UI_FLAG_HIDE_NAVIGATION，即使它现在还没有</li>
     * <li>{@link View#SYSTEM_UI_FLAG_LAYOUT_STABLE} >>> 当使用其他布局标志时，我们希望稳定地查看给出的内容插入 fitSystemWindows(Rect)</li>
     * <li>{@link View#SYSTEM_UI_FLAG_LIGHT_STATUS_BAR} >>> 请求状态栏绘制一个与灯光状态栏背景兼容的模式</li>
     * <li>{@link View#SYSTEM_UI_FLAG_LOW_PROFILE} >>> View已请求系统UI进入不显眼的“低调”模式</li>
     * <li>{@link View#SYSTEM_UI_FLAG_VISIBLE} >>> View已请求系统UI（状态栏）可见（默认）</li>
     * <li>{@link View#SYSTEM_UI_LAYOUT_FLAGS} >>> 与系统UI相关的可能影响布局的标志</li>
     * </ul>
     */
    public static void hideSystemBar(@NonNull Window window, boolean hideStatus, boolean hideNavigation) {
        int visibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            visibility |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        } else {
            visibility |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        // | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
        // | View.SYSTEM_UI_FLAG_FULLSCREEN // 隐藏状态栏
        if (hideStatus) {
            visibility |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        if (hideNavigation) {
            visibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        window.getDecorView().setSystemUiVisibility(visibility);
    }

    public static void showSystemBar(@NonNull Window window) {
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }
}