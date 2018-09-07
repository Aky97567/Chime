package com.akshayltc.chime.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akshayltc.chime.R;
import com.akshayltc.chime.callback.BaseListener;
import com.akshayltc.chime.utilities.BaseEvents;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;

public class FileBrowserAdapter extends RecyclerView.Adapter {

    private File currentDir;
    private File baseDir;
    private File[] subdirList;
    private File[] fileList;
    private int subDirCount;
    private int fileCount;
    private Context context;
    private BaseListener mBaseListener;

    public FileBrowserAdapter (File baseDir, Context context, BaseListener mBaseListener) {
        this.baseDir = baseDir;
        this.context = context;
        this.mBaseListener = mBaseListener;
        updateDir(baseDir);
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FileViewHolder(LayoutInflater.from(context).inflate(R.layout.file_browser_directory_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        ((FileViewHolder) viewHolder).position = i;
        if ( i == 0 && !currentDir.getName().equals( baseDir.getName() )) {
            ((FileViewHolder) viewHolder).name.setText("..");
            ((FileViewHolder) viewHolder).name.setTextColor(ContextCompat.getColor(context, R.color.secondary_text));
            ((FileViewHolder) viewHolder).RLFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateDir(currentDir.getParentFile());
                }
            });
        } else if ( currentDir.getName().equals( baseDir.getName() )  ) {
            if ( i < subDirCount ) {
                ((FileViewHolder) viewHolder).name.setText(subdirList[i].getName());
                ((FileViewHolder) viewHolder).name.setTextColor(ContextCompat.getColor(context, R.color.secondary_text));
                final File fileItem = subdirList[((FileViewHolder) viewHolder).position];
                ((FileViewHolder) viewHolder).RLFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (fileItem.isDirectory()) {
                            updateDir(fileItem);
                        } else {
                            Toast.makeText(context, "Not a Directory", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                ((FileViewHolder) viewHolder).name.setText(fileList[i-subDirCount].getName());
                ((FileViewHolder) viewHolder).name.setTextColor(ContextCompat.getColor(context, R.color.cyan_text));
                final File fileItem = fileList[((FileViewHolder) viewHolder).position - subDirCount];
                ((FileViewHolder) viewHolder).RLFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (fileItem.isDirectory()) {
                            updateDir(fileItem);
                        } else {
                            Toast.makeText(context, "Not a Directory", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } else if ( i != 0 && !currentDir.getName().equals( baseDir.getName() )  ) {
            if ( i <= subDirCount ) {
                ((FileViewHolder) viewHolder).name.setText(subdirList[i - 1].getName());
                ((FileViewHolder) viewHolder).name.setTextColor(ContextCompat.getColor(context, R.color.secondary_text));
                final File fileItem = subdirList[((FileViewHolder) viewHolder).position - 1];
                ((FileViewHolder) viewHolder).RLFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (fileItem.isDirectory()) {
                            updateDir(fileItem);
                        } else {
                            Toast.makeText(context, "Not a Directory", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                   ((FileViewHolder) viewHolder).name.setText(fileList[i - (subDirCount + 1)].getName());
                ((FileViewHolder) viewHolder).name.setTextColor(ContextCompat.getColor(context, R.color.cyan_text));
                final File fileItem = fileList[((FileViewHolder) viewHolder).position - (subDirCount + 1)];
                ((FileViewHolder) viewHolder).RLFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (fileItem.isDirectory()) {
                            updateDir(fileItem);
                        } else {
                            Toast.makeText(context, "Not a Directory", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    public void updateDir(File fileItem) {
        Toast.makeText(context, "Directory", Toast.LENGTH_SHORT).show();
        currentDir = fileItem;
        getFiles();
        notifyDataSetChanged();
        mBaseListener.onEvent( BaseEvents.DIRECTORY_CHANGED, 0, currentDir);
    }

    @Override
    public int getItemCount() {
        if ( currentDir.getName().equals( baseDir.getName() ) ) {
            return subdirList.length + fileList.length;
        } else {
            return subdirList.length + fileList.length + 1;
        }
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RelativeLayout RLFile;
        int position;

        public FileViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = itemView.findViewById(R.id.fileName);
            RLFile = itemView.findViewById(R.id.RLFile);

        }
    }

    public void getFiles() {
        subdirList = currentDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        subDirCount = subdirList.length;

        fileList = currentDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getPath().endsWith(".mp3")
                        || file.getPath().endsWith(".flac") || file.getPath().endsWith(".wav")
                        || file.getPath().endsWith(".ogg");
            }
        });
        fileCount = fileList.length;
    }

    public void sortByName(File[] fileset) {
        Arrays.sort(fileset, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                if ((f1 == null) || (f2 == null))
                {
                    return 0;
                }

                return 0;
            }
        });
    }

}
