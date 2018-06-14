package com.brave.employ.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brave.common.utils.cipher.CipherMode;
import com.brave.common.utils.cipher.CipherPadding;
import com.brave.common.utils.cipher.CipherUtils;
import com.brave.common.utils.network.NetworkUtils;
import com.brave.common.utils.span.SpanUtils;
import com.brave.common.utils.time.TimeUtils;
import com.brave.employ.R;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：主页
 */
public class HomeActivity extends FragmentActivity {
    private static final String TAG = "HomeActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        textView = findViewById(R.id.text_view);
        // NetworkUtils.openNetworkSettings();

        SpanUtils.newInstance()
                .setText("撒旦撒大大所大转世王老五所多多jdhkjasdhaksdsjdbajfgasadja转世王老五lskdjl圣诞节卡号地块金黄色即可转世王老五到哈萨克大家哈桑,转世王老五")
                .setForegroundColor(Color.BLUE, "转世王老五")
                .setBackgroundColor(Color.YELLOW, "老五")
                .setURL("www.baidu.com", "撒")
                .setTextSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(HomeActivity.this, "onclick : dsjdbajf", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(true);
                    }
                }, "dsjdbajf")
                .setTextSpan(new ImageSpan(HomeActivity.this, R.mipmap.ic_launcher), "金黄色")
                .setClickMovement(true)
                .setHighlightColorRes(android.R.color.transparent)
                .into(textView);
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

        testNetwork();
    }

    private int flag = 0;

    public void testOnClick(View v) {
        switch (flag) {
            case 0:
                NetworkUtils.openDataEnabled();
                break;
            case 1:
                NetworkUtils.openWifiEnabled();
                break;
            case 2:
                NetworkUtils.closeDataEnabled();
                NetworkUtils.closeWifiEnabled();
                break;
            default:
                flag = -1;
                break;
        }
        flag++;
    }

    private void testNetwork() {
        Log.d(TAG, "testNetwork: isAvailable = " + NetworkUtils.isAvailable());
        Log.d(TAG, "testNetwork: isConnected = " + NetworkUtils.isConnected());
        Log.d(TAG, "testNetwork: isWifiConnected = " + NetworkUtils.isWifiConnected());
        Log.d(TAG, "testNetwork: isDataEnabled = " + NetworkUtils.isDataEnabled());
        Log.d(TAG, "testNetwork: isWifiEnabled = " + NetworkUtils.isWifiEnabled());

        Log.i(TAG, "testTime: -------------------------------------");


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