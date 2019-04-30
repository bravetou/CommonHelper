package com.brave.common.utils.permission;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ： Permissions 管理工具类
 */
public final class PermissionsUtils {
    private PermissionsUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getContext();
    }

    public static PermissionsRequest with(@NonNull Activity activity) {
        return PermissionsRequest.newInstance(activity);
    }

    public static PermissionsRequest with(@NonNull Fragment fragment) {
        return PermissionsRequest.newInstance(fragment.getActivity());
    }

    public static PermissionsRequest with(@NonNull androidx.fragment.app.Fragment fragment) {
        return PermissionsRequest.newInstance(fragment.getActivity());
    }

    /**
     * 是否需要动态注册权限
     *
     * @return true ： 需要 <br/> false ： 反之
     */
    public static boolean isNeedRegister() {
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
    public static boolean hasPermissions(String... permissions) {
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
    public static boolean hasPermissions(int... grantResults) {
        int count = null == grantResults ? 0 : grantResults.length;
        for (int i = 0; i < count; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}