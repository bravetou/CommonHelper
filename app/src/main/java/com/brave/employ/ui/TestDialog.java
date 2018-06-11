package com.brave.employ.ui;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.brave.common.base.DialogBuilder;
import com.brave.common.base.v4.CommonDialog;
import com.brave.employ.R;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ：
 */
@SuppressLint("ValidFragment")
public class TestDialog extends CommonDialog<TestDialog.Builder> {
    protected TestDialog(Builder builder) {
        super(builder);
    }

    @Override
    protected int loadDialogTheme() {
        return R.style.AppTheme;
    }

    @Override
    protected int loadLayoutResId() {
        return R.layout.dialog_test;
    }

    public static class Builder extends DialogBuilder {
        public Builder(Activity activity) {
            super(activity);
        }

        public void show() {
            new TestDialog(this).show();
        }

        @Override
        public Builder setCancelable(boolean cancelable) {
            return (Builder) super.setCancelable(cancelable);
        }
    }
}