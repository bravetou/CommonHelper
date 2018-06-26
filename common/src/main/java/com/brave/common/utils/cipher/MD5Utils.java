package com.brave.common.utils.cipher;

import android.text.TextUtils;

import com.brave.common.CommonConfig;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ： MD5加密 工具类
 */
public final class MD5Utils {
    private MD5Utils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Charset getDefaultCharset() {
        return CommonConfig.getDefaultCharset();
    }

    /**
     * 32位
     */
    public static String encrypt(byte[] text) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(text);
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();// 加密
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 32位
     */
    public static String encrypt(String value) {
        return encrypt(value.getBytes(getDefaultCharset()));
    }

    /**
     * 16位
     */
    public static String encrypt16(byte[] text) {
        String encrypt = encrypt(text);
        if (TextUtils.isEmpty(encrypt)) {
            return "";
        }
        return encrypt.substring(8, 24);
    }

    /**
     * 16位
     */
    public static String encrypt16(String value) {
        return encrypt16(value.getBytes(getDefaultCharset()));
    }
}