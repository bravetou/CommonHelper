package com.brave.common.helper.permission;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ：Permissions 请求类
 */
public class PermissionsRequest {
    private Activity activity;
    private int requestCode = 521; // 默认权限请求码
    private String[] permissions; // 实际请求权限数组

    private List<String> tempPermissions; // 临时请求权限集合

    public static PermissionsRequest newInstance() {
        return new PermissionsRequest();
    }

    private PermissionsRequest() {
        if (null == tempPermissions) {
            tempPermissions = new ArrayList<>();
        }
    }

    public PermissionsRequest with(@NonNull Activity activity) {
        this.activity = activity;
        return this;
    }

    public PermissionsRequest with(@NonNull android.app.Fragment fragment) {
        this.activity = fragment.getActivity();
        return this;
    }

    public PermissionsRequest with(@NonNull android.support.v4.app.Fragment fragment) {
        this.activity = fragment.getActivity();
        return this;
    }

    public PermissionsRequest setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    public PermissionsRequest addPermissions(String... permissions) {
        int count = null == permissions ? 0 : permissions.length;
        for (int i = 0; i < count; i++) {
            tempPermissions.add(permissions[i]);
        }
        return this;
    }

    public PermissionsRequest removePermissions(String... permissions) {
        int count = null == permissions ? 0 : permissions.length;
        for (int i = 0; i < count; i++) {
            tempPermissions.remove(permissions[i]);
        }
        return this;
    }

    public PermissionsRequest clearPermissions() {
        if (null != tempPermissions) {
            tempPermissions.clear();
        }
        return this;
    }

    /**
     * 执行权限请求
     */
    public PermissionsRequest execute() {
        if (null == activity) {
            throw new RuntimeException("Request permission on an empty object.");
        }
        int count = null == tempPermissions ? 0 : tempPermissions.size();
        permissions = new String[count];
        for (int i = 0; i < count; i++) {
            permissions[i] = tempPermissions.get(i);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, requestCode);
        }
        // 请求完成之后 清空临时权限
        clearPermissions();
        return this;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public String[] getPermissions() {
        return permissions;
    }
}