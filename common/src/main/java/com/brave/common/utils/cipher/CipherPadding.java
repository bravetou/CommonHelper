package com.brave.common.utils.cipher;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ：枚举 加解密 填充方式
 */
public enum CipherPadding {
    PKCS5("PKCS5Padding"),
    PKCS7("PKCS7Padding"),
    No("NoPadding"),
    ISO10126("ISO10126Padding"),
    ANSIX923("ANSIX923Padding");

    String value;

    CipherPadding(String value) {
        this.value = value;
    }
}