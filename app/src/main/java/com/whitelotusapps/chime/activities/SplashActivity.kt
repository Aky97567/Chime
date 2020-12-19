package com.whitelotusapps.chime.activities

import android.os.Bundle
import android.os.Handler
import com.whitelotusapps.chime.R

/**
 * Created by Admin on 2017-07-05.
 */
class SplashActivity : BaseActivity() {
    //region global variables
    //endregion
    //region Application overrides
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler.postDelayed({
            startFileBrowser()
            finish()
        }, 200)
    } //endregion
}