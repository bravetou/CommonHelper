package com.brave.common.utils.encrypt;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ：枚举 加解密 模式
 */
public enum EncryptMode {
    ECB("ECB"),
    CBC("CBC"),
    CTR("CTR"),
    OFB("OFB"),
    CFB("CFB");

    String value;

    EncryptMode(String value) {
        this.value = value;
    }
}