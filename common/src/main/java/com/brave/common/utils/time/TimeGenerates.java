package com.brave.common.utils.time;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/26 <br/>
 * <b>description</b> ：时间 生成
 */
public class TimeGenerates {
    // 时间戳(ms)
    private final long time;
    // 时间格式
    private String pattern;
    // 时间源
    private Object source;

    public static TimeGenerates newInstance(@NonNull long time) {
        return new TimeGenerates(time);
    }

    private TimeGenerates(long time) {
        this.time = time;
    }

    /**
     * 设置时间源
     */
    public TimeGenerates source(Object source) {
        this.source = source;
        return this;
    }

    /**
     * 日期和时间格式的模式
     */
    public TimeGenerates pattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    /**
     * 生成指定时间格式
     */
    public String format() {
        if (null == pattern) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(time);
    }

    /**
     * 转时间戳
     */
    public long toTimestamp() {
        if (source instanceof Date) {
            Date date = (Date) source;
            return date.getTime();
        } else if (source instanceof String) {
            String str = (String) source;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                Date date = simpleDateFormat.parse(str);
                return date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0L;
    }

    public String getYear() {
        pattern("yyyy");
        return format();
    }

    public String getMonth() {
        pattern("MM");
        return format();
    }

    public String getDay() {
        pattern("dd");
        return format();
    }

    public String getHour() {
        pattern("HH");
        return format();
    }

    public String getMinute() {
        pattern("mm");
        return format();
    }

    public String getSecond() {
        pattern("ss");
        return format();
    }

    public String getMillisecond() {
        pattern("SSS");
        return format();
    }

    public String getWeek() {
        pattern("E");
        return format();
    }
}