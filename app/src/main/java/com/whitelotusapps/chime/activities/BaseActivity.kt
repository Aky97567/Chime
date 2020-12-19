package com.whitelotusapps.chime.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

open class BaseActivity : AppCompatActivity() {
    protected fun startFileBrowser() {
        val intent = Intent(baseContext, FileBrowserActivity::class.java)
        startActivity(intent)
    }

    protected fun checkPermissions(permissions: Array<String?>, requestCode: Int, activity: Activity?): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (PackageManager.PERMISSION_GRANTED != baseContext.checkSelfPermission(permission!!)) {
                    return try {
                        ActivityCompat.requestPermissions(activity!!, permissions, requestCode)
                        false
                    } catch (e: Exception) {
                        e.printStackTrace()
                        throw e
                    }
                }
            }
        }
        return true
    }
}