package com.brave.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.brave.common.helper.ActivityHelper;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ：个人常用配置（全局）
 */
public class CommonConfig {
    private static class SingletonHolder {
        private volatile static CommonConfig mInstance = new CommonConfig();
    }

    public static CommonConfig getInstance() {
        return SingletonHolder.mInstance;
    }

    private CommonConfig() {
    }

    // 维护全局应用程序状态的基类
    private Application application;
    // 全局应用程序监听
    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            // 把本 Activity 加入存活集合中
            ActivityHelper.getInstance().addActivity(activity);
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
            ActivityHelper.getInstance().removeActivity(activity);
        }
    };

    /**
     * 初始化 个人常用配置
     *
     * @param app 维护全局应用程序状态的基类
     */
    public void initialize(@NonNull Application app) {
        application = app;
        app.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public Application getApplication() {
        return application;
    }

    public Context getContext() {
        return application.getApplicationContext();
    }
}