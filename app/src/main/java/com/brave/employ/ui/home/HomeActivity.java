package com.brave.employ.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.brave.common.base.v4.CommonActivity;
import com.brave.common.utils.ScreenUtils;
import com.brave.common.utils.cipher.MD5Utils;
import com.brave.common.utils.io.SPUtils;
import com.brave.common.utils.network.NetworkUtils;
import com.brave.common.utils.system.SystemBarUtils;
import com.brave.employ.R;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：主页
 */
public class HomeActivity extends CommonActivity {
    private static final String TAG = "HomeActivity";
    private TextView textView;

    @Override
    protected int loadLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = findViewById(R.id.text_view);
        // NetworkUtils.openNetworkSettings();

//        SpanUtils.newInstance()
//                .setText("撒旦撒大大所大转世王老五所多多jdhkjasdhaksdsjdbajfgasadja转世王老五lskdjl圣诞节卡号地块金黄色即可转世王老五到哈萨克大家哈桑,转世王老五")
//                .setForegroundColor(Color.BLUE, "转世王老五")
//                .setBackgroundColor(Color.YELLOW, "老五")
//                .setURL("www.baidu.com", "撒")
//                .setTextSpan(new ClickableSpan() {
//                    @Override
//                    public void onClick(View widget) {
//                        Toast.makeText(HomeActivity.this, "onclick : dsjdbajf", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void updateDrawState(TextPaint ds) {
//                        ds.setColor(Color.BLUE);
//                        ds.setUnderlineText(true);
//                    }
//                }, "dsjdbajf")
//                .setTextSpan(new ImageSpan(HomeActivity.this, R.mipmap.ic_launcher), "金黄色")
//                .setClickMovement(true)
//                .setHighlightColorRes(android.R.color.transparent)
//                .into(textView);


        // CommonActivity

        

        SystemBarUtils.setSystemBarColor(this, Color.BLUE);


//        StatusBarUtils.setIntegration(this);
//        StatusBarUtils.setStatusBarColor(this, ColorUtils.argb(0.66f, Color.BLUE));
//        NavBarUtils.setNavBarColor(this, Color.BLUE);

        boolean flag = ScreenUtils.hasNavigationBar(this);
        Log.d(TAG, "onCreate: " + flag);
    }

    private int flag = 0;

    public void testOnClick(View v) {
        Log.d(TAG, "testOnClick: =========================" + (flag % 6));
        switch (flag % 6) {
            case 0:
                SystemBarUtils.hideSystemBar(getWindow(), true, false);
                break;
            case 1:
                SystemBarUtils.showSystemBar(getWindow());
                break;
            case 2:
                SystemBarUtils.hideSystemBar(getWindow(), false, true);
                break;
            case 3:
                SystemBarUtils.showSystemBar(getWindow());
                break;
            case 4:
                SystemBarUtils.hideSystemBar(getWindow(), true, true);
                break;
            case 5:
                SystemBarUtils.showSystemBar(getWindow());
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
}