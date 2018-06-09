package com.brave.employ.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

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
    }
}