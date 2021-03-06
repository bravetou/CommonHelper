package com.brave.common.utils;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： 键盘 相关工具类
 */
public final class KeyboardUtils {
    private KeyboardUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    /**
     * 避免 输入法 面板 遮挡
     * <p>在 manifest.xml 中 activity 中设置</p>
     * <p>android:windowSoftInputMode="adjustPan"</p>
     */
    private static Context getContext() {
        return CommonConfig.getContext();
    }

    /**
     * 获取 InputMethodManager 对象
     */
    public static InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private static View getEditText(View v) {
        if (v instanceof EditText) {
            return v;
        }
        if (v instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = getEditText(viewGroup.getChildAt(i));
                if (null != view) {
                    return view;
                }
            }
        }
        return null;
    }

    /**
     * 动态显示软键盘
     */
    public static void showSoftInput(@Nullable Activity activity) {
        View view = activity.getCurrentFocus();
        if (null == view) {
            view = getEditText(activity.findViewById(android.R.id.content));
        }
        if (null == view) {
            return;
        }
        InputMethodManager imm = getInputMethodManager();
        if (imm == null) {
            return;
        }
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 动态显示软键盘
     *
     * @param view 视图
     */
    public static void showSoftInput(@Nullable View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        InputMethodManager imm = getInputMethodManager();
        if (imm == null) {
            return;
        }
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 动态显示软键盘并把布局顶上去
     *
     * @param activity Activity
     * @param view     视图对象
     */
    public static void showSoftInputTop(@NonNull Activity activity, @Nullable View view) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        showSoftInput(view);
    }

    /**
     * 动态隐藏软键盘
     *
     * @param activity Activity
     */
    public static void hideSoftInput(@Nullable Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) {
            return;
        }
        InputMethodManager imm = getInputMethodManager();
        if (imm == null) {
            return;
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    /**
     * 动态隐藏软键盘
     *
     * @param view 视图
     */
    public static void hideSoftInput(@Nullable View view) {
        InputMethodManager imm = getInputMethodManager();
        if (imm == null) {
            return;
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    /**
     * 软件盘是否弹出
     *
     * @param activity Activity
     */
    public static boolean isSoftInputShow(@NonNull Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = getInputMethodManager();
            return inputmanger.isActive() && activity.getWindow().getCurrentFocus() != null;
        }
        return false;
    }

    /**
     * 键盘 开关（即：显示 => 隐藏，隐藏 => 显示）
     */
    public static void toggleSoftInput() {
        InputMethodManager imm = getInputMethodManager();
        if (imm == null) {
            return;
        }
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}