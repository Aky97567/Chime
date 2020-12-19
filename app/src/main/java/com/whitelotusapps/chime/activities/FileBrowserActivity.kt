package com.whitelotusapps.chime.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whitelotusapps.chime.R
import com.whitelotusapps.chime.adapter.FileBrowserAdapter
import com.whitelotusapps.chime.callback.BaseListener
import com.whitelotusapps.chime.utilities.AppConstants
import java.io.File

class FileBrowserActivity : BaseActivity() {
    private var fileBrowser: RecyclerView? = null
    private var fileBrowserAdapter: FileBrowserAdapter? = null
    private val mBaseListener = BaseListener { event, position, params ->
        val currentDir = (params[0] as File).absolutePath
        supportActionBar!!.title = currentDir
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_browser)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        init()
        if (checkPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        AppConstants.REQUEST_CODE_STORAGE,
                        this)) {
            loadFiles()
        }
    }

    private fun init() {
        fileBrowser = findViewById<View>(R.id.fileBrowser) as RecyclerView
    }

    private fun loadFiles() {
        val sd = Environment.getExternalStorageDirectory()
        val sdDirList = sd.listFiles()
        supportActionBar!!.title = sd.absolutePath
        fileBrowserAdapter = FileBrowserAdapter(sd, this, mBaseListener)
        fileBrowser!!.layoutManager = LinearLayoutManager(this)
        fileBrowser!!.adapter = fileBrowserAdapter
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            AppConstants.REQUEST_CODE_STORAGE -> {
                var granted = true
                if (grantResults.size > 0) {
                    for (result in grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            granted = false
                        }
                    }
                }
                if (!granted) {
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
                } else {
                    loadFiles()
                }
            }
        }
    }
}