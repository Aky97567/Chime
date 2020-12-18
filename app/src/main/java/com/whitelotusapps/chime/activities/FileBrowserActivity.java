package com.whitelotusapps.chime.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import com.whitelotusapps.chime.R;
import com.whitelotusapps.chime.adapter.FileBrowserAdapter;
import com.whitelotusapps.chime.callback.BaseListener;
import com.whitelotusapps.chime.utilities.AppConstants;
import com.whitelotusapps.chime.utilities.BaseEvents;

import java.io.File;

public class FileBrowserActivity extends BaseActivity {

    private RecyclerView fileBrowser;
    private FileBrowserAdapter fileBrowserAdapter;

    private BaseListener mBaseListener = new BaseListener() {
        @Override
        public void onEvent(BaseEvents event, int position, Object... params) {
            String currentDir = ((File) params[0]).getAbsolutePath();
            getSupportActionBar().setTitle(currentDir);
        }
    };

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

        fileBrowserAdapter = new FileBrowserAdapter(sd, this, mBaseListener);
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
