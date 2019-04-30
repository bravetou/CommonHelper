package com.brave.common.utils.cipher;

import androidx.annotation.NonNull;
import android.util.Base64;

import com.brave.common.CommonConfig;

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
 * <b>createTime</b> ： 2018/6/26 <br/>
 * <b>description</b> ：加（解）密 生成
 */
public class CipherGenerates {
    // 编码格式
    private final Charset charset;
    // 密钥
    private final byte[] secretKey;
    // 文本
    private byte[] text;
    // 算法
    private String algorithm;
    // 模式
    private String mode;
    // 填充
    private String padding;

    public static CipherGenerates newInstance(@NonNull byte[] secretKey) {
        return new CipherGenerates(secretKey);
    }

    private CipherGenerates(@NonNull byte[] secretKey) {
        this.charset = CommonConfig.getDefaultCharset();
        this.secretKey = secretKey;
    }

    /**
     * 加载加解密文本
     *
     * @param text 文本
     */
    public CipherGenerates load(@NonNull byte[] text) {
        this.text = text;
        return this;
    }

    /**
     * 加载加解密文本
     *
     * @param text 文本
     */
    public CipherGenerates load(@NonNull String text) {
        this.text = text.getBytes(charset);
        return this;
    }

    /**
     * 设置算法
     */
    public CipherGenerates algorithm(@NonNull String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    /**
     * 设置模式
     */
    public CipherGenerates mode(@NonNull String mode) {
        this.mode = mode;
        return this;
    }

    /**
     * 设置模式
     */
    public CipherGenerates mode(@NonNull CipherMode cipherMode) {
        this.mode = cipherMode.getValue();
        return this;
    }

    /**
     * 设置填充
     */
    public CipherGenerates padding(@NonNull String padding) {
        this.padding = padding;
        return this;
    }

    /**
     * 设置填充
     */
    public CipherGenerates padding(@NonNull CipherPadding cipherPadding) {
        this.padding = cipherPadding.getValue();
        return this;
    }

    /**
     * 生成加解密 byte[]
     */
    public byte[] generates(int opmode) {
        byte[] original = null;
        try {
            if (null == algorithm) {
                algorithm = "AES";
            }
            if (null == mode) {
                mode = "ECB";
            }
            if (null == padding) {
                padding = "PKCS5Padding";
            }
            // 设置 密钥与加解密算法
            SecretKeySpec spec = new SecretKeySpec(secretKey, algorithm);
            // 设置 加解密算法/模式/填充 组合
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
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
     * 加密（编码）
     */
    public byte[] encrypt() {
        return generates(Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密（解码）
     */
    public String decrypt() {
        return new String(generates(Cipher.DECRYPT_MODE));
    }

    /**
     * 转换为Base64
     */
    public CipherGenerate toBase64() {
        return new CipherGenerate(this, "Base64");
    }

    /**
     * 加（解）密 生成指定格式
     */
    public class CipherGenerate {
        private final CipherGenerates cipherGenerates;
        private final String type;

        public CipherGenerate(@NonNull CipherGenerates cipherGenerates, @NonNull String type) {
            this.cipherGenerates = cipherGenerates;
            this.type = type;
        }

        /**
         * 加密（编码）
         */
        public String encrypt() {
            String encrypt = "";
            switch (type) {
                case "Base64":
                    encrypt = Base64.encodeToString(cipherGenerates.encrypt(), Base64.DEFAULT)
                            .replace("\n", "")
                            .replace("\t", "")
                            .trim();
                    break;
                default:
                    break;
            }
            return encrypt;
        }

        /**
         * 解密（解码）
         */
        public String decrypt() {
            switch (type) {
                case "Base64":
                    cipherGenerates.text = Base64.decode(cipherGenerates.text, Base64.DEFAULT);
                    break;
                default:
                    break;
            }
            return cipherGenerates.decrypt();
        }
    }
}