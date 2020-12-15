package com.whitelotusapps.chime.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

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