package com.brave.common.utils.network;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/13 <br/>
 * <b>description</b> ：网络 类型
 * <ul>
 * <li>{@link NetworkType#NO} ： 无网络</li>
 * <li>{@link NetworkType#UNKNOWN} ： 未知网络</li>
 * <li>{@link NetworkType#WIFI} ： WIFI</li>
 * <li>{@link NetworkType#G2} ： 2G</li>
 * <li>{@link NetworkType#G3} ： 3G</li>
 * <li>{@link NetworkType#G4} ： 4G</li>
 * <li>{@link NetworkType#G5} ： 5G</li>
 * </ul>
 */
public enum NetworkType {
    NO(-1, "NETWORK_NO"), // 无网络
    UNKNOWN(0, "NETWORK_UNKNOWN"), // 未知的网络
    WIFI(1, "NETWORK_WIFI"), // wifi
    G2(2, "NETWORK_2G"), // 2G
    G3(3, "NETWORK_3G"), // 3G
    G4(4, "NETWORK_4G"), // 4G
    G5(5, "NETWORK_5G"); // 5G

    private int index;
    private String value;

    NetworkType(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}