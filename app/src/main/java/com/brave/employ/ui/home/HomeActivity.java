package com.brave.employ.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.brave.common.helper.ActivityHelper;
import com.brave.common.utils.ScreenUtils;
import com.brave.employ.R;

import java.util.List;

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

        List<Activity> activitys = ActivityHelper.getInstance().getStackActivitys();
        for (Activity activity : activitys) {
            Log.d(TAG, "onStart: " + activity);
        }
        Log.d(TAG, "onStart: StackTop = " + ActivityHelper.getInstance().getStackTopActivity());

        Log.d(TAG, "onStart: ----------------------------------------------------------");

        Log.d(TAG, "onStart: " + ScreenUtils.getScreenWidth());
        Log.d(TAG, "onStart: " + ScreenUtils.getScreenHeight());

        Log.d(TAG, "onStart: ----------------------------------------------------------");

        Log.d(TAG, "onStart: " + ScreenUtils.isLandscape());
        Log.d(TAG, "onStart: " + ScreenUtils.isPortrait());

        Log.d(TAG, "onStart: ----------------------------------------------------------");

        Log.d(TAG, "onStart: " + ScreenUtils.isPhablet());

        Log.d(TAG, "onStart: ----------------------------------------------------------");

        // ScreenUtils.setFullScreen(this);

        Log.d(TAG, "onStart: ----------------------------------------------------------");

        // ScreenUtils.setSleepTime(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Activity> activitys = ActivityHelper.getInstance().getStackActivitys();
                for (Activity activity : activitys) {
                    Log.d(TAG, "run: " + activity);
                }
            }
        }, 3 * 1000);
    }
}