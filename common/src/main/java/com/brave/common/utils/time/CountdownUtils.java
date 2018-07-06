package com.brave.common.utils.time;


import android.os.CountDownTimer;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： 倒计时 工具类
 */
public abstract class CountdownUtils extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountdownUtils(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public CountdownUtils(int millisInFuture, int countDownInterval) {
        super(millisInFuture * 1000, countDownInterval * 1000);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        onTick(((int) (millisUntilFinished / 1000)));
    }

    /**
     * 指定的间隔时间调用（s）
     *
     * @param millisUntilFinished 余下的时间
     */
    public abstract void onTick(int millisUntilFinished);

    public abstract void onFinish();
}