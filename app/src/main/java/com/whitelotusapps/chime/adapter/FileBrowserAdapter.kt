package com.whitelotusapps.chime.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.whitelotusapps.chime.R
import com.whitelotusapps.chime.callback.BaseListener
import com.whitelotusapps.chime.utilities.BaseEvents
import java.io.File
import java.util.*

class FileBrowserAdapter(private val baseDir: File, private val context: Context, private val mBaseListener: BaseListener) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var currentDir: File? = null
    private var subdirList: Array<File>? = null
    private var fileList: Array<File>? = null
    private var subDirCount = 0
    private var fileCount = 0
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FileViewHolder {
        return FileViewHolder(LayoutInflater.from(context).inflate(R.layout.file_browser_directory_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        (viewHolder as FileViewHolder).pos = i
        if (i == 0 && currentDir!!.name != baseDir.name) {
            viewHolder.name.text = ".."
            viewHolder.name.setTextColor(ContextCompat.getColor(context, R.color.secondary_text))
            viewHolder.RLFile.setOnClickListener { updateDir(currentDir!!.parentFile) }
        } else if (currentDir!!.name == baseDir.name) {
            if (i < subDirCount) {
                viewHolder.name.text = subdirList!![i].name
                viewHolder.name.setTextColor(ContextCompat.getColor(context, R.color.secondary_text))
                val fileItem = subdirList!![viewHolder.position]
                viewHolder.RLFile.setOnClickListener {
                    if (fileItem.isDirectory) {
                        updateDir(fileItem)
                    } else {
                        Toast.makeText(context, "Not a Directory", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                viewHolder.name.text = fileList!![i - subDirCount].name
                viewHolder.name.setTextColor(ContextCompat.getColor(context, R.color.cyan_text))
                val fileItem = fileList!![viewHolder.position - subDirCount]
                viewHolder.RLFile.setOnClickListener {
                    if (fileItem.isDirectory) {
                        updateDir(fileItem)
                    } else {
                        Toast.makeText(context, "Not a Directory", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else if (i != 0 && currentDir!!.name != baseDir.name) {
            if (i <= subDirCount) {
                viewHolder.name.text = subdirList!![i - 1].name
                viewHolder.name.setTextColor(ContextCompat.getColor(context, R.color.secondary_text))
                val fileItem = subdirList!![viewHolder.position - 1]
                viewHolder.RLFile.setOnClickListener {
                    if (fileItem.isDirectory) {
                        updateDir(fileItem)
                    } else {
                        Toast.makeText(context, "Not a Directory", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                viewHolder.name.text = fileList!![i - (subDirCount + 1)].name
                viewHolder.name.setTextColor(ContextCompat.getColor(context, R.color.cyan_text))
                val fileItem = fileList!![viewHolder.position - (subDirCount + 1)]
                viewHolder.RLFile.setOnClickListener {
                    if (fileItem.isDirectory) {
                        updateDir(fileItem)
                    } else {
                        Toast.makeText(context, "Not a Directory", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun updateDir(fileItem: File?) {
        Toast.makeText(context, "Directory", Toast.LENGTH_SHORT).show()
        currentDir = fileItem
        files
        notifyDataSetChanged()
        mBaseListener.onEvent(BaseEvents.DIRECTORY_CHANGED, 0, currentDir)
    }

    override fun getItemCount(): Int {
        return if (currentDir!!.name == baseDir.name) {
            subDirCount + fileCount
        } else {
            subDirCount + fileCount
        }
    }

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var RLFile: RelativeLayout
        var pos = 0

        init {
            // get the reference of item views
            name = itemView.findViewById(R.id.fileName)
            RLFile = itemView.findViewById(R.id.RLFile)
        }
    }

    val files: Unit
        get() {
            subdirList = currentDir!!.listFiles { file -> file.isDirectory }
            subDirCount = if (subdirList != null) subdirList!!.size else 0
            fileList = currentDir!!.listFiles { file ->
                (file.path.endsWith(".mp3")
                        || file.path.endsWith(".flac") || file.path.endsWith(".wav")
                        || file.path.endsWith(".ogg"))
            }
            fileCount = if (fileList != null) fileList!!.size else 0
        }

    fun sortByName(fileset: Array<File>?) {
        Arrays.sort(fileset) { f1, f2 ->
            if (f1 == null || f2 == null) {
                0
            } else 0
        }
    }

    init {
        updateDir(baseDir)
    }
}