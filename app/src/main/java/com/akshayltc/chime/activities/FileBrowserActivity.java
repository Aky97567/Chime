package com.akshayltc.chime.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akshayltc.chime.R;
import com.akshayltc.chime.adapter.FileBrowserAdapter;
import com.akshayltc.chime.utilities.AppConstants;

import java.io.File;

public class FileBrowserActivity extends BaseActivity {

    RecyclerView fileBrowser;
    FileBrowserAdapter fileBrowserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_browser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        if(checkPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                AppConstants.REQUEST_CODE_STORAGE,
                this )) {
            loadFiles();
        }
    }

    private void init() {
        fileBrowser = (RecyclerView) findViewById(R.id.fileBrowser);
    }

    private void loadFiles() {
        File sd = Environment.getExternalStorageDirectory();
        File[] sdDirList = sd.listFiles();

        getSupportActionBar().setTitle( sd.getAbsolutePath() );

        fileBrowserAdapter = new FileBrowserAdapter(sd, this);
        fileBrowser.setLayoutManager(new LinearLayoutManager(this));
        fileBrowser.setAdapter(fileBrowserAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case AppConstants.REQUEST_CODE_STORAGE: {
                boolean granted = true;
                if (grantResults.length > 0) {
                    for (int result:grantResults) {
                        if ( result != PackageManager.PERMISSION_GRANTED) {
                            granted = false;
                        }
                    }

                }
                if ( !granted ) {
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                } else {
                    loadFiles();
                }
            }
        }
    }

}
