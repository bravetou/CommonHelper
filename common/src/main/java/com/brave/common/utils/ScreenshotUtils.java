package com.brave.common.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ：截图 工具类
 */
public final class ScreenshotUtils {
    private ScreenshotUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     */
    public static Bitmap getScreenshot(@NonNull Activity activity) {
        return getViewScreenshot(activity.getWindow().getDecorView());
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     */
    public static Bitmap getScreenshotNoStatusBar(@NonNull Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top,
                width = ScreenUtils.getScreenWidth(),
                height = ScreenUtils.getScreenHeight();
        return Bitmap.createBitmap(getScreenshot(activity), 0, statusBarHeight,
                width, height - statusBarHeight);
    }

    /**
     * 获取当前View的截图
     */
    public static Bitmap getViewScreenshot(@NonNull View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap bmp = v.getDrawingCache();
        int width = v.getMeasuredWidth();
        int height = v.getMeasuredHeight();
        Bitmap bitmap = Bitmap.createBitmap(bmp, 0, 0, width, height);
        v.destroyDrawingCache();
        return bitmap;
    }
}