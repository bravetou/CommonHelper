package com.brave.common.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： 时间 相关工具类
 */
public class TimeUtils {
    // 格式化工具类
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    // 默认的时间格式
    private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";

    // 时间格式
    private String mFormat = null;
    // 需要格式化的内容
    private Object mObj = null;
    // 日期类
    private Date mDate = null;
    // 是否反转  <br/> true >>>  时间格式转时间戳 <br/> false >>>  反之
    // private boolean isReversal = false;

    private static class SingletonHolder {
        private volatile static TimeUtils mInstance = new TimeUtils();
    }

    public static TimeUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private TimeUtils() {
    }

    /**
     * 指定的模式字符串 应用到日期格式
     *
     * @param format 日期格式
     */
    public TimeUtils format(String format) {
        mFormat = format;
        return getInstance();
    }

    /**
     * 指定 格式化 的时间
     *
     * @param obj 时间
     */
    public TimeUtils time(Object obj) {
        mObj = obj;
        return getInstance();
    }

    /**
     * 指定格式化的时间类（优先匹配）
     *
     * @param date 时间类
     */
    public TimeUtils date(Date date) {
        mDate = date;
        return getInstance();
    }

    /**
     * 获取 匹配格式 结果（String类型）
     */
    public String get() {
        if (null != mFormat) {
            simpleDateFormat.applyPattern(mFormat);
        } else {
            simpleDateFormat.applyPattern(defaultFormat);
        }
        String result = "";
        if (null != mDate) {
            result = simpleDateFormat.format(mDate);
        } else if (null != mObj) {
            result = simpleDateFormat.format(mObj);
        } else {
            result = simpleDateFormat.format(getCurrentTime());
        }
        mFormat = null;
        mDate = null;
        mObj = null;
        return result;
    }

    /**
     * 获取 时间戳
     */
    public long asTime() {
        long time = 0L;
        if (null != mDate) {
            time = mDate.getTime();
        } else {
            if (null != mFormat) {
                simpleDateFormat.applyPattern(mFormat);
            } else {
                simpleDateFormat.applyPattern(defaultFormat);
            }
            try {
                Date date = simpleDateFormat.parse(String.valueOf(mObj));
                time = date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mFormat = null;
        mDate = null;
        mObj = null;
        return time;
    }

    public long getLong() {
        return Long.parseLong(get());
    }

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public String getYear() {
        format("yyyy");
        return get();
    }

    public String getMonth() {
        format("MM");
        return get();
    }

    public String getDay() {
        format("dd");
        return get();
    }

    public String getHour() {
        format("HH");
        return get();
    }

    public String getMinute() {
        format("mm");
        return get();
    }

    public String getSecond() {
        format("ss");
        return get();
    }

    public String getMillisecond() {
        format("SSS");
        return get();
    }

    public String getWeek() {
        format("E");
        return get();
    }
}