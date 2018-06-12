package com.brave.common.utils.cipher;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： 异或加密工具类<br/>参考链接：http://www.cnblogs.com/whoislcj/p/5944917.html
 */
public final class XorUtils {
    private XorUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    /**
     * 固定 key 的方式加密
     * <p>
     * 这种方式加密解密 算法一样
     * <p>
     * 加密：byte[] bytes = encryptAsFix("liyi".getBytes());
     * 解密：String str = new String(encryptAsFix(bytes));
     *
     * @param bytes 待加密数据
     * @return 加密后的数据
     */
    public static byte[] encryptAsFix(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = 0; i < len; i++) {
            bytes[i] ^= key;
        }
        return bytes;
    }


    /**
     * 非固定 key 的方式加密
     *
     * @param bytes 待加密数据
     * @return 加密后的数据
     */
    public static byte[] encrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (bytes[i] ^ key);
            key = bytes[i];
        }
        return bytes;
    }

    /**
     * 解密
     *
     * @param bytes 待解密数据
     * @return 解密后的数据
     */
    public byte[] decrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = len - 1; i > 0; i--) {
            bytes[i] = (byte) (bytes[i] ^ bytes[i - 1]);
        }
        bytes[0] = (byte) (bytes[0] ^ key);
        return bytes;
    }
}