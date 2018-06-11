package com.brave.common.utils.encrypt;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ：举 加解密 填充方式
 */
public enum EncryptPadding {
    PKCS5("PKCS5Padding"),
    PKCS7("PKCS7Padding"),
    No("NoPadding"),
    ISO10126("ISO10126Padding"),
    ANSIX923("ANSIX923Padding");

    String value;

    EncryptPadding(String value) {
        this.value = value;
    }
}