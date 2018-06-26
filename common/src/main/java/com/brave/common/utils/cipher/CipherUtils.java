package com.brave.common.utils.cipher;

import android.support.annotation.NonNull;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/26 <br/>
 * <b>description</b> ：加（解）密 工具类
 */
public final class CipherUtils {
    private CipherUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    public static CipherGenerates with(byte[] secretKey) {
        byte[] defaultSecretKey = CommonConfig.getDefaultSecretKey();
        if (null == secretKey && null == defaultSecretKey) {
            throw new RuntimeException("The key cannot be empty.");
        }
        if (null != secretKey) {
            return CipherGenerates.newInstance(secretKey);
        }
        return CipherGenerates.newInstance(defaultSecretKey);
    }

    public static CipherGenerates with(@NonNull String secretKey) {
        return with(secretKey.getBytes(CommonConfig.getDefaultCharset()));
    }

    public static CipherGenerates with() {
        return with((byte[]) null);
    }
}