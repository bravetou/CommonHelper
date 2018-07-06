package com.brave.common.utils.time;


import android.os.CountDownTimer;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： 倒计时 工具类
 */
public class CountdownUtils {
    /* 倒计时的间隔时间 */
    private long intervalTime = 0L;
    /* 倒计时的总时间 */
    private long totalTime = 0L;
    /* 系统倒计时类 */
    private CountDownTimer countDownTimer;
    /* 倒计时监听 */
    private CountDownListener countDownListener;

    private CountdownUtils() {
    }

    public static CountdownUtils newInstance() {
        return new CountdownUtils();
    }

    /**
     * 设置 倒计时 时间间隔
     *
     * @param intervalTime 间隔时间
     */
    public CountdownUtils setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
        return this;
    }

    /**
     * 设置 倒计时 总时间
     *
     * @param totalTime 总时间
     */
    public CountdownUtils setTotalTime(long totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    /**
     * 设置 倒计时 监听
     *
     * @param countDownListener 倒计时监听
     */
    public CountdownUtils setCountDownListener(CountDownListener countDownListener) {
        this.countDownListener = countDownListener;
        return this;
    }

    /**
     * 初始化倒计时类
     */
    private void initCountDownTimer() {
        if (null == countDownTimer) {
            countDownTimer = new CountDownTimer(totalTime, intervalTime) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (null != countDownListener) {
                        countDownListener.onTick(millisUntilFinished);
                    }
                }

                @Override
                public void onFinish() {
                    if (null != countDownListener) {
                        countDownListener.onFinish();
                    }
                }
            };
        }
    }

    /**
     * 开始倒计时
     */
    public CountdownUtils start() {
        initCountDownTimer();
        if (null != countDownTimer) {
            countDownTimer.start();
        }
        return this;
    }

    /**
     * 停止倒计时
     */
    public void stop() {
        if (null != countDownTimer) {
            countDownTimer.cancel();
            if (null != countDownListener) {
                countDownListener.onFinish();
            }
        }
    }

    public interface CountDownListener {
        /**
         * 倒计时间隔时间调用
         *
         * @param remainingTime 余下的时间
         */
        void onTick(long remainingTime);

        /**
         * 倒计时完成
         */
        void onFinish();
    }

    public long getIntervalTime() {
        return intervalTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (null != countDownTimer) {
            countDownTimer.cancel();
        }
        countDownListener = null;
    }
}