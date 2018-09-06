package com.akshayltc.chime.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected void startFileBrowser() {
        Intent intent = new Intent(getBaseContext(),FileBrowserActivity.class);
        startActivity(intent);
    }

    protected boolean checkPermissions(String[] permissions, int requestCode, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for(String permission:permissions) {
                if (PackageManager.PERMISSION_GRANTED != getBaseContext().checkSelfPermission(permission)) {
                    try {
                        ActivityCompat.requestPermissions(activity, permissions, requestCode);
                        return false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
        }
        return true;
    }
}