package com.brave.common.helper.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.brave.common.base.CommonApplication;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ： Permissions 帮助类
 */
public class PermissionsHelper {
    private Context context;

    private static class SingletonHolder {
        private volatile static PermissionsHelper mInstance = new PermissionsHelper();
    }

    public static PermissionsHelper getInstance() {
        return SingletonHolder.mInstance;
    }

    private PermissionsHelper() {
    }

    private Context getContext() {
        if (null != context) {
            return context;
        }
        return CommonApplication.getContext();
    }

    /**
     * 如果调用此方法则 {@link #getContext()} 中使用的 {@link CommonApplication#getContext()} 失效<br/>
     * 如需再次使用 {@link CommonApplication#getContext()} 需要置空 null
     */
    public PermissionsHelper with(Context context) {
        this.context = context;
        return this;
    }

    /**
     * 是否需要动态注册权限
     *
     * @return true ： 需要 <br/> false ： 反之
     */
    public boolean isNeedRegister() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        }
        return false;
    }

    /**
     * 是否已拥有权限
     *
     * @return true ： 已拥有 <br/> false ： 反之
     */
    public boolean hasPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int count = null == permissions ? 0 : permissions.length;
            for (int i = 0; i < count; i++) {
                // 检测 您 是否 开启了权限
                if (getContext().checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 是否已拥有权限
     *
     * @return true ： 已拥有 <br/> false ： 反之
     */
    public boolean hasPermissions(int... grantResults) {
        int count = null == grantResults ? 0 : grantResults.length;
        for (int i = 0; i < count; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}