package com.brave.common;

import android.content.Context;

import com.brave.common.base.CommonApplication;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ：android 常用工具的配置
 */
public class CommonConfig {
    private static volatile CommonConfig mInstance = null;
    private DefaultCommonInitializer initializer;

    public static void setDefaultCommonInitializer(DefaultCommonInitializer initializer) {
        if (null == mInstance) {
            synchronized (CommonConfig.class) {
                if (null == mInstance) {
                    mInstance = new CommonConfig(initializer);
                }
            }
        }
    }

    public static CommonConfig getInstance() {
        if (null == mInstance) {
            setDefaultCommonInitializer(new DefaultCommonInitializer() {
                @Override
                public void initialize(CommonConfig config) {
                    config.setContext(CommonApplication.getContext());
                }
            });
        }
        return mInstance;
    }

    private CommonConfig(DefaultCommonInitializer initializer) {
        this.initializer = initializer;
    }

    private Context mContext; // 应用程序的全局信息

    private int permissionRequestCode = 521; // 权限请求码

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public int getPermissionRequestCode() {
        return permissionRequestCode;
    }

    public void setPermissionRequestCode(int permissionRequestCode) {
        this.permissionRequestCode = permissionRequestCode;
    }
}