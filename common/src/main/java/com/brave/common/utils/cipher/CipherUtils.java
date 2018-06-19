package com.brave.common.utils.cipher;

import android.text.TextUtils;
import android.util.Base64;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/11 <br/>
 * <b>description</b> ： 加（解）密 工具类
 */
public class CipherUtils {
    // 默认编码格式
    private Charset defaultCharset;
    // 默认加（解）密 算法
    private String defaultAlgorithm;
    // 默认加（解）密 模式
    private String defaultMode;
    // 默认加（解）密 填充
    private String defaultPadding;
    // 默认的加（解）密 算法/模式/填充 组合
    private String defaultCipher;

    // 加解密 密钥
    private String secretKey;
    private byte[] key;
    // 加解密 文本
    private String content;
    private byte[] text;

    public static CipherUtils newInstance() {
        return new CipherUtils();
    }

    private CipherUtils() {
        this.defaultCharset = Charset.forName("UTF-8");
        this.defaultAlgorithm = "AES";
        this.defaultMode = CipherMode.ECB.getValue();
        this.defaultPadding = CipherPadding.PKCS5.getValue();
        // defaultCipher = defaultType + "/" + defaultMode + "/" + defaultPadding;
    }

    /**
     * 获取加密（解密）之后的byte数组
     *
     * @param key    密码
     * @param text   文本
     * @param opmode 模式 - （加密{@link Cipher#ENCRYPT_MODE}/解密{@link Cipher#DECRYPT_MODE}）
     */
    private byte[] getByteTexts(byte[] key, byte[] text, int opmode) {
        byte[] original = null;
        try {
            // 设置 秘钥与类型
            SecretKeySpec spec = new SecretKeySpec(key, defaultAlgorithm);
            // 设置 类型/模式/填充 组合
            Cipher cipher = Cipher.getInstance(defaultCipher);
            // 设置 模式 >>> 加密（解密）和SecretKeySpec
            cipher.init(opmode, spec);
            // 获取 加密（解密）之后的 byte数组
            original = cipher.doFinal(text);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return original;
    }

    /**
     * 设置加解密编码
     *
     * @param charsetName 编码名
     */
    public CipherUtils setCharset(String charsetName) {
        this.defaultCharset = Charset.forName(charsetName);
        return this;
    }

    /**
     * 设置加解密编码
     */
    public CipherUtils setCharset(Charset defaultCharset) {
        this.defaultCharset = defaultCharset;
        return this;
    }

    /**
     * 设置加解密算法{? >>> AES、DES...}
     */
    public CipherUtils setAlgorithm(String defaultAlgorithm) {
        this.defaultAlgorithm = defaultAlgorithm;
        return this;
    }

    /**
     * 设置加解密算法
     */
    public CipherUtils setMode(CipherMode cipherMode) {
        this.defaultMode = cipherMode.getValue();
        return this;
    }

    /**
     * 设置加解密模式
     */
    public CipherUtils setMode(String defaultMode) {
        this.defaultMode = defaultMode;
        return this;
    }

    /**
     * 设置加解密填充
     */
    public CipherUtils setPadding(CipherPadding cipherPadding) {
        this.defaultPadding = cipherPadding.getValue();
        return this;
    }

    /**
     * 设置加解密填充
     */
    public CipherUtils setPadding(String defaultPadding) {
        this.defaultPadding = defaultPadding;
        return this;
    }

    /**
     * 设置 密钥
     */
    public CipherUtils setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    /**
     * 设置 密钥
     */
    public CipherUtils setSecretKey(byte[] key) {
        this.key = key;
        return this;
    }

    /**
     * 设置 加密内容
     */
    public CipherUtils setText(String content) {
        this.content = content;
        return this;
    }

    /**
     * 设置 加密内容
     */
    public CipherUtils setText(byte[] text) {
        this.text = text;
        return this;
    }

    /**
     * 不能有空的密钥和文本
     */
    private void isNull() {
        defaultCipher = defaultAlgorithm + "/" + defaultMode + "/" + defaultPadding;
        System.out.println("defaultCipher = " + defaultCipher);
        if (!TextUtils.isEmpty(secretKey)) {
            key = secretKey.getBytes(defaultCharset);
        }
        if (null == key) {
            // fountainfountain
            key = "fountainfountain".getBytes(defaultCharset);
        }
        if (!TextUtils.isEmpty(content)) {
            text = content.getBytes(defaultCharset);
        }
        if (null == text) {
            // brave tou
            this.text = "brave tou".getBytes(defaultCharset);
        }
    }

    /**
     * 数据加密
     */
    public byte[] encrypt() {
        isNull();
        return getByteTexts(key, text, Cipher.ENCRYPT_MODE);
    }

    /**
     * 数据加密
     */
    public String encryptToBase64() {
        return Base64.encodeToString(encrypt(), Base64.DEFAULT)
                .replace("\n", "")
                .replace("\t", "")
                .trim();
    }

    /**
     * 数据解密
     */
    public byte[] decrypt() {
        isNull();
        return getByteTexts(key, text, Cipher.DECRYPT_MODE);
    }

    /**
     * 数据解密
     */
    public String decryptByBase64() {
        if (!TextUtils.isEmpty(content)) {
            text = Base64.decode(content
                    .replace("\n", "")
                    .replace("\t", "")
                    .trim(), Base64.DEFAULT);
            content = null;
        }
        return new String(decrypt());
    }
}