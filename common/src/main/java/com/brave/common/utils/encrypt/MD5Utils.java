package com.brave.common.utils.encrypt;

import android.text.TextUtils;

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

    /**
     * 32位 MD5加密 方法
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
     * 32位 MD5加密 方法
     */
    public static String encrypt(String value) {
        return encrypt(value.getBytes());
    }

    /**
     * 16位 MD5加密 方法
     */
    public static String encrypt16(byte[] text) {
        String encrypt = encrypt(text);
        if (TextUtils.isEmpty(encrypt)) {
            return "";
        }
        return encrypt.substring(8, 24);
    }

    /**
     * 16位 MD5加密 方法
     */
    public static String encrypt16(String value) {
        return encrypt16(value.getBytes());
    }

    /**
     * 32位 MD5加密 方法（密文加密）
     *
     * @param pwd  密码
     * @param text 明文
     */
    public static String encrypt(String pwd, String text) {
        // 文本16位加密
        String encrypt = encrypt16(text);
        if (TextUtils.isEmpty(encrypt)) {
            return "";
        }
        if (!TextUtils.isEmpty(pwd)) {
            pwd = "fountain";
        }
        // 密码 + 16位加密的文本 再次加密
        encrypt = encrypt16(pwd + encrypt);
        // 明文 + （密码 + 16位加密的文本） 再次32位加密
        return encrypt(text + encrypt);
    }

    /**
     * 16位 MD5加密 方法（密文加密）
     *
     * @param pwd  密码
     * @param text 明文
     */
    public static String encrypt16(String pwd, String text) {
        // 文本16位加密
        String encrypt = encrypt16(text);
        if (TextUtils.isEmpty(encrypt)) {
            return "";
        }
        if (!TextUtils.isEmpty(pwd)) {
            pwd = "fountain";
        }
        // 密码 + 16位加密的文本 再次加密
        encrypt = encrypt16(pwd + encrypt);
        // 明文 + （密码 + 16位加密的文本） 再次32位加密
        return encrypt16(text + encrypt);
    }
}