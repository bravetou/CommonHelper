package com.brave.employ.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.brave.common.base.v4.CommonActivity;
import com.brave.common.utils.cipher.CipherPadding;
import com.brave.common.utils.cipher.CipherUtils;
import com.brave.common.utils.cipher.MD5Utils;
import com.brave.common.utils.time.TimeUtils;
import com.brave.employ.R;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：主页
 */
public class HomeActivity extends CommonActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected int loadLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 测试加解密
        testCipher();
        // 测试Base64加解密
        testCipherToBase64();
        // 测试MD5加密
        testMD5();
        // 测试时间转换
        testTime();
    }

    private void testTime() {
        long time = TimeUtils.getSystemTime();

        Log.d(TAG, "testTime: " + time);

        String format = TimeUtils.with(time)
                .pattern("yyyy年MM月dd日 HH:mm:ss")
                .format();

        Log.d(TAG, "testTime: " + format);

        long timestamp = TimeUtils.with()
                .pattern("yyyy年MM月dd日 HH:mm:ss")
                .source(format)
                .toTimestamp();

        Log.d(TAG, "testTime: " + timestamp);
    }

    private void testMD5() {
        String text = "First test \"MD5\" plus decryption!";

        Log.d(TAG, "testMD5: " + text);

        String encrypt = MD5Utils.encrypt(text);

        Log.d(TAG, "testMD5: " + encrypt);

        String encrypt16 = MD5Utils.encrypt16(text);

        Log.d(TAG, "testMD5: " + encrypt16);

    }

    private void testCipherToBase64() {
        String text = "First test \"Base64\" plus decryption!";

        Log.d(TAG, "testCipherToBase64: " + text);

        String encrypt = CipherUtils.with("fountainfountain")
                .load(text)
                .algorithm("AES")
                .mode("ECB")
                .padding(CipherPadding.PKCS5)
                .toBase64()
                .encrypt();

        Log.d(TAG, "testCipherToBase64: " + encrypt);

        String decrypt = CipherUtils.with("fountainfountain")
                .load(encrypt)
                .algorithm("AES")
                .mode("ECB")
                .padding(CipherPadding.PKCS5)
                .toBase64()
                .decrypt();

        Log.d(TAG, "testCipherToBase64: " + decrypt);
    }

    private void testCipher() {
        String text = "First test normal encryption and decryption!";

        Log.d(TAG, "testCipher: " + text);

        byte[] encrypt = CipherUtils.with("fountainfountain")
                .load(text)
                .algorithm("AES")
                .mode("ECB")
                .padding(CipherPadding.PKCS5)
                .encrypt();

        Log.d(TAG, "testCipher: " + encrypt);

        String decrypt = CipherUtils.with("fountainfountain")
                .load(encrypt)
                .algorithm("AES")
                .mode("ECB")
                .padding(CipherPadding.PKCS5)
                .decrypt();

        Log.d(TAG, "testCipher: " + decrypt);
    }
}