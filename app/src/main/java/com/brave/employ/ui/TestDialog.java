package com.brave.employ.ui;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;

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

    public static class Builder extends CommonDialog.DialogBuilder {
        public Builder(FragmentActivity activity) {
            super(activity);
        }

        public void show() {
            new TestDialog(this).show();
        }
    }
}
