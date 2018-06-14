package com.brave.common.utils;

import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/14 <br/>
 * <b>description</b> ：颜色 转换 工具类
 */
public final class ColorUtils {
    /**
     * 生成 ARGB 颜色
     */
    @ColorInt
    public static int argb(
            @IntRange(from = 0, to = 255) int alpha,
            @IntRange(from = 0, to = 255) int red,
            @IntRange(from = 0, to = 255) int green,
            @IntRange(from = 0, to = 255) int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    @IntRange(from = 0, to = 255)
    public static int alpha(@ColorInt int color) {
        return color >>> 24;
    }

    @IntRange(from = 0, to = 255)
    public static int red(int color) {
        return (color >> 16) & 0xFF;
    }

    @IntRange(from = 0, to = 255)
    public static int green(int color) {
        return (color >> 8) & 0xFF;
    }

    @IntRange(from = 0, to = 255)
    public static int blue(int color) {
        return color & 0xFF;
    }

    /**
     * 生成 ARGB 颜色
     */
    @ColorInt
    public static int argb(@FloatRange(from = 0, to = 1) float alpha,
                           @FloatRange(from = 0, to = 1) float red,
                           @FloatRange(from = 0, to = 1) float green,
                           @FloatRange(from = 0, to = 1) float blue) {
        return ((int) (alpha * 255.0f + 0.5f) << 24) |
                ((int) (red * 255.0f + 0.5f) << 16) |
                ((int) (green * 255.0f + 0.5f) << 8) |
                (int) (blue * 255.0f + 0.5f);
    }

    /**
     * 生成 ARGB 颜色
     */
    @ColorInt
    public static int argb(@FloatRange(from = 0, to = 1) float alpha,
                           @IntRange(from = 0, to = 255) int red,
                           @IntRange(from = 0, to = 255) int green,
                           @IntRange(from = 0, to = 255) int blue) {
        return ((int) (alpha * 255.0f + 0.5f) << 24) | (red << 16) | (green << 8) | blue;
    }

    /**
     * 生成 ARGB 颜色
     */
    @ColorInt
    public static int argb(@FloatRange(from = 0, to = 1) float alpha, @ColorInt int color) {
        return ((int) (alpha * 255.0f + 0.5f) << 24) | (red(color) << 16) | (green(color) << 8) | blue(color);
    }
}