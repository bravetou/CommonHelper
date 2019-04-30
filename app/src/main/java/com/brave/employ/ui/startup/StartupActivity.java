package com.brave.employ.ui.startup;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.brave.employ.R;
import com.brave.employ.ui.home.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：启动页
 */
public class StartupActivity extends FragmentActivity implements View.OnClickListener {
    // 跳过按钮
    private TextView skip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        init();
    }

    // 初始化
    protected void init() {
        // 取出 skip
        skip = findViewById(R.id.skip);
        // 设置点击事件监听
        skip.setOnClickListener(this);
        // 执行计时操作，3s后执行线程任务
        timer.schedule(timerTask, 1000 * 3);
    }

    // 创建线程工具
    protected Timer timer = new Timer();
    // 创建线程任务
    protected TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            enterInto();
        }
    };

    /**
     * 跳转主页
     */
    protected void enterInto() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    /**
     * 跳过等待
     */
    protected void skipWait() {
        if (null != timer) {
            timer.cancel();
        }
        if (null != timerTask) {
            timerTask.cancel();
        }
        enterInto();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skip:
                // 跳过
                skipWait();
                break;
            default:
                // 其他所有按钮
                break;
        }
    }
}