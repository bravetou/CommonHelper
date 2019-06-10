package com.brave.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.brave.common.CommonConfig;

import java.io.Serializable;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/13 <br/>
 * <b>description</b> ： Activity 跳转相关
 */
public final class IntentUtils {
    private IntentUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getContext();
    }

    private static final Intent getIntent() {
        return new Intent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 显示启动Activity
     *
     * @param packageContext 当前Activity
     * @param cls            跳转Activity
     * @param args           需要传递的参数 => 取出名 （arg + 下标索引[arg0,arg1,arg2,arg3...]）
     */
    public static void openActivity(@NonNull Context packageContext,
                                    @NonNull Class<?> cls, Object... args) {
        final Intent intent = getIntent();
        intent.setClass(packageContext, cls);
        int count = null == args ? 0 : args.length;
        for (int i = 0; i < count; i++) {
            putExtra(intent, "arg" + i, args[i]);
        }
        getContext().startActivity(intent);
    }

    /**
     * 隐式启动Activity
     *
     * @param action 注册时的action android:name
     * @param args   需要传递的参数 => 取出名 （arg + 下标索引[arg0,arg1,arg2,arg3...]）
     */
    public static void openActivity(@NonNull String action, Object... args) {
        if (TextUtils.isEmpty(action)) {
            throw new RuntimeException("action cannot be empty");
        }
        final Intent intent = getIntent();
        intent.setAction(action);
        int count = null == args ? 0 : args.length;
        for (int i = 0; i < count; i++) {
            putExtra(intent, "arg" + i, args[i]);
        }
        getContext().startActivity(intent);
    }

    private static void putExtra(Intent intent, String key, Object obj) {
        if (obj instanceof Integer) {
            intent.putExtra(key, (Integer) obj);
        } else if (obj instanceof Float) {
            intent.putExtra(key, (Float) obj);
        } else if (obj instanceof Double) {
            intent.putExtra(key, (Double) obj);
        } else if (obj instanceof Character) {
            intent.putExtra(key, (Character) obj);
        } else if (obj instanceof Byte) {
            intent.putExtra(key, (Byte) obj);
        } else if (obj instanceof Boolean) {
            intent.putExtra(key, (Boolean) obj);
        } else if (obj instanceof String) {
            intent.putExtra(key, (String) obj);
        } else if (obj instanceof Long) {
            intent.putExtra(key, (Long) obj);
        } else if (obj instanceof Short) {
            intent.putExtra(key, (Short) obj);
        } else if (obj instanceof Serializable) {
            intent.putExtra(key, (Serializable) obj);
        } else if (obj instanceof Parcelable) {
            intent.putExtra(key, (Parcelable) obj);
        } else if (obj instanceof Bundle) {
            intent.putExtra(key, (Bundle) obj);
        } else if (obj instanceof Parcelable[]) {
            intent.putExtra(key, (Parcelable[]) obj);
        } else if (obj instanceof boolean[]) {
            intent.putExtra(key, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            intent.putExtra(key, (byte[]) obj);
        } else if (obj instanceof char[]) {
            intent.putExtra(key, (char[]) obj);
        } else if (obj instanceof double[]) {
            intent.putExtra(key, (double[]) obj);
        } else if (obj instanceof float[]) {
            intent.putExtra(key, (float[]) obj);
        } else if (obj instanceof int[]) {
            intent.putExtra(key, (int[]) obj);
        } else if (obj instanceof String[]) {
            intent.putExtra(key, (String[]) obj);
        } else if (obj instanceof long[]) {
            intent.putExtra(key, (long[]) obj);
        } else if (obj instanceof short[]) {
            intent.putExtra(key, (short[]) obj);
        } else {
            intent.putExtra(key, String.valueOf(obj));
        }
    }
}