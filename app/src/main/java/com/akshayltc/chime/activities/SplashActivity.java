package com.akshayltc.chime.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.akshayltc.chime.R;

/**
 * Created by Admin on 2017-07-05.
 */

public class SplashActivity extends BaseActivity {

    //region global variables

    //endregion

    //region Application overrides
    final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startFileBrowser();
                finish();
            }
        }, 200);
    }


    //endregion

}
