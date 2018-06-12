package com.brave.common.utils.cipher;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ：枚举 加解密 模式
 */
public enum CipherMode {
    ECB("ECB"),
    CBC("CBC"),
    CTR("CTR"),
    OFB("OFB"),
    CFB("CFB");

    String value;

    CipherMode(String value) {
        this.value = value;
    }
}