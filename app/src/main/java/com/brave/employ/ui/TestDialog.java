package com.brave.employ.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brave.common.base.DialogBuilder;
import com.brave.common.base.v4.CommonDialog;
import com.brave.common.helper.StatusBarHelper;
import com.brave.employ.R;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ：
 */
@SuppressLint("ValidFragment")
public class TestDialog extends CommonDialog<TestDialog.Builder> {
    private static final String TAG = "TestDialog";

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        StatusBarHelper.getInstance().setStatusBarColor(getDialog(), Color.RED, rootView);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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