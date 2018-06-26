package com.brave.common.utils.time;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/26 <br/>
 * <b>description</b> ：时间 相关工具类
 */
public final class TimeUtils {
    private TimeUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    /**
     * 获取系统时间
     */
    public static long getSystemTime() {
        return System.currentTimeMillis();
    }

    public static TimeGenerates with(long time) {
        return TimeGenerates.newInstance(time);
    }

    public static TimeGenerates with() {
        return TimeGenerates.newInstance(getSystemTime());
    }
}