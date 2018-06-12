package com.brave.common.utils;

import android.content.Context;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： （密度）单位 转换工具类
 */
public final class DensityUtils {
    private DensityUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getInstance().getContext();
    }

    /**
     * dp转px
     */
    //    public static float dp2px(float dpVal) {
    //        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
    //                dpVal, getContext().getResources().getDisplayMetrics());
    //    }

    /**
     * sp转px
     */
    //    public static float sp2px(float spVal) {
    //        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
    //                spVal, getContext().getResources().getDisplayMetrics());
    //    }

    /**
     * px转dp
     */
    //    public static float px2dp(float pxVal) {
    //        return (pxVal / getContext().getResources().getDisplayMetrics().density);
    //    }

    /**
     * px转sp
     */
    //    public static float px2sp(float pxVal) {
    //        return (pxVal / getContext().getResources().getDisplayMetrics().scaledDensity);
    //    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(float pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}