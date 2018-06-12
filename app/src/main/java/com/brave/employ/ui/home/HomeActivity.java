package com.brave.employ.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.brave.common.utils.cipher.CipherMode;
import com.brave.common.utils.cipher.CipherPadding;
import com.brave.common.utils.cipher.CipherUtils;
import com.brave.common.utils.time.TimeUtils;
import com.brave.employ.R;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：主页
 */
public class HomeActivity extends FragmentActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Log.d(TAG, "onCreate: " + AppUtils.getInstance().getVersionCode());
        //Log.d(TAG, "onCreate: " + AppUtils.getInstance().getVersionName());
        //
        //if (PermissionsHelper.getInstance().isNeedRegister()) {
        //    boolean hasPermissions = PermissionsHelper.getInstance().hasPermissions(Manifest.permission.READ_PHONE_STATE);
        //    Log.d(TAG, "onCreate: " + hasPermissions);
        //    if (!hasPermissions) {
        //        PermissionsRequest execute = PermissionsRequest.newInstance()
        //                .with(this)
        //                .setRequestCode(521)
        //                .addPermissions(Manifest.permission.READ_PHONE_STATE
        //                )
        //                .execute();
        //        String[] permissions = execute.getPermissions();
        //        for (String permission : permissions) {
        //            Log.d(TAG, "onCreate: " + permission);
        //        }
        //        Log.d(TAG, "onCreate: " + execute.getRequestCode());
        //    }
        //}
        //
        //new TestDialog.Builder(this)
        //        .show();

        testAES();

        testDES();

        testTime();
    }

    private void testTime() {
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().getYear());
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().getMonth());
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().getDay());
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().getHour());
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().getMinute());
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().getSecond());
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().getMillisecond());
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().getWeek());
        Log.i(TAG, "testTime: -------------------------------------");
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().format("yyyy年MM月dd日 HH:mm:ss").time(TimeUtils.getCurrentTime()).get());
        Log.d(TAG, "testTime: " + TimeUtils.getInstance().format("yyyy年MM月dd日 HH:mm:ss").time("2018年06月12日 15:42:00").asTime());
    }

    private void testDES() {
        String encrypt = CipherUtils.newInstance()
                .setDefaultAlgorithm("DES")
                .setDefaultMode(CipherMode.ECB)
                .setDefaultPadding(CipherPadding.PKCS5)
                .setSecretKey("abcdefgh")
                .setText("我是你陶大哥！")
                .encryptToBase64();

        Log.d(TAG, "testDES: " + encrypt);

        String decrypt = CipherUtils.newInstance()
                .setDefaultAlgorithm("DES")
                .setDefaultMode(CipherMode.ECB)
                .setDefaultPadding(CipherPadding.PKCS5)
                .setSecretKey("abcdefgh")
                .setText(encrypt)
                .decryptByBase64();

        Log.d(TAG, "testDES: " + new String(decrypt));
    }

    private void testAES() {
        String encrypt = CipherUtils.newInstance()
                .setDefaultAlgorithm("AES")
                .setDefaultMode(CipherMode.ECB)
                .setDefaultPadding(CipherPadding.PKCS5)
                .setSecretKey("zhongshangpuhuis")
                .setText("我是你陶大哥！")
                .encryptToBase64();

        Log.d(TAG, "testAES: " + encrypt);


        String decrypt = CipherUtils.newInstance()
                .setDefaultAlgorithm("AES")
                .setDefaultMode(CipherMode.ECB)
                .setDefaultPadding(CipherPadding.PKCS5)
                .setSecretKey("zhongshangpuhuis")
                .setText(encrypt)
                .decryptByBase64();

        Log.d(TAG, "testAES: " + decrypt);
    }
}