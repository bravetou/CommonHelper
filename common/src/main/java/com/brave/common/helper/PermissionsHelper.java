package com.brave.common.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.brave.common.CommonConfig;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2017/12/8<br/>
 * Description : Manifest管理器<br/>
 */
public class PermissionsHelper {
    private Context mContext;

    private static class SingletonHolder {
        private volatile static PermissionsHelper mInstance = new PermissionsHelper();
    }

    public static PermissionsHelper getInstance() {
        return SingletonHolder.mInstance;
    }

    private PermissionsHelper() {
        mContext = CommonConfig.getInstance().getContext();
    }

    /*public PermissionsHelper with(Activity activity) {
        this.activity = activity;
        return getInstance();
    }*/

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
     * 检测权限是否已注册
     *
     * @return true ： 已注册 <br/> false ： 反之
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean isRegistered(String... permissions) {
        if (isNeedRegister()) {
            int count = null == permissions ? 0 : permissions.length;
            for (int i = 0; i < count; i++) {
                // 检测 您 是否 开启了权限
                if (mContext.checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检测权限是否已注册
     *
     * @return true ： 已注册 <br/> false ： 反之
     */
    public boolean isRegistered(int... grantResults) {
        int count = null == grantResults ? 0 : grantResults.length;
        for (int i = 0; i < count; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 动态请求权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(Activity activity, String... permissions) {
        if (isNeedRegister()) {
            activity.requestPermissions(permissions, CommonConfig.getInstance().getPermissionRequestCode());
        }
    }
}