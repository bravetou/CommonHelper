package com.brave.common.base;

import android.app.Activity;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ：dialog Builder
 */
public abstract class DialogBuilder {
    private Activity activity;
    private boolean cancelable;

    public DialogBuilder(Activity activity) {
        this.activity = activity;
    }

    public DialogBuilder(Activity activity, boolean cancelable) {
        this.activity = activity;
        setCancelable(cancelable);
    }

    public DialogBuilder setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public Activity getActivity() {
        return activity;
    }

    public boolean isCancelable() {
        return cancelable;
    }
}