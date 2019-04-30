package com.brave.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.brave.common.utils.ActivityUtils;

import java.nio.charset.Charset;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ：个人常用配置（全局）
 */
public final class CommonConfig {
    // 维护全局应用程序状态的基类
    private static Application application;
    // 全局应用程序监听
    private static Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            // 把本 Activity 加入存活集合中
            ActivityUtils.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            // 把本 Activity 从存活集合中移除并杀死
            ActivityUtils.getInstance().removeActivity(activity);
        }
    };

    private CommonConfig() {
        throw new RuntimeException("cannot be instantiated");
    }

    /**
     * 初始化 个人常用配置
     *
     * @param app 维护全局应用程序状态的基类
     */
    public static void init(@NonNull Application app) {
        application = app;
        app.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public static Application getApplication() {
        if (null == application) {
            throw new RuntimeException("Use this method(CommonConfig.init(app)) after initialization");
        }
        return application;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    // 默认编码格式
    private static Charset defaultCharset = Charset.forName("UTF-8");

    /**
     * 获取默认编码格式
     */
    public static Charset getDefaultCharset() {
        return CommonConfig.defaultCharset;
    }

    /**
     * 设置默认编码格式
     *
     * @param defaultCharset 编码格式
     */
    public static void setDefaultCharset(@NonNull Charset defaultCharset) {
        CommonConfig.defaultCharset = defaultCharset;
    }

    /**
     * 设置默认编码格式
     *
     * @param defaultCharset 编码格式
     */
    public static void setDefaultCharset(@NonNull String defaultCharset) {
        CommonConfig.defaultCharset = Charset.forName(defaultCharset);
    }

    // 默认密钥
    private static byte[] defaultSecretKey;

    /**
     * 获取默认密钥
     */
    public static byte[] getDefaultSecretKey() {
        return CommonConfig.defaultSecretKey;
    }

    /**
     * 设置默认密钥
     */
    public static void setDefaultSecretKey(@NonNull byte[] defaultSecretKey) {
        CommonConfig.defaultSecretKey = defaultSecretKey;
    }

    /**
     * 设置默认密钥
     */
    public static void setDefaultSecretKey(@NonNull String defaultSecretKey) {
        CommonConfig.defaultSecretKey = defaultSecretKey.getBytes(CommonConfig.defaultCharset);
    }
}