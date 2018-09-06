package com.akshayltc.chime.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
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

public class FileBrowserAdapter extends RecyclerView.Adapter {

    private File currentDir;
    private File baseDir;
    private Context context;
    private BaseListener mBaseListener;

    public FileBrowserAdapter (File baseDir, Context context, BaseListener mBaseListener) {
        this.baseDir = baseDir;
        this.currentDir = baseDir;
        this.context = context;
        this.mBaseListener = mBaseListener;
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
            ((FileViewHolder) viewHolder).RLFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateDir(currentDir.getParentFile());
                }
            });
        } else if ( currentDir.getName().equals( baseDir.getName() )  ) {
            ((FileViewHolder) viewHolder).name.setText(currentDir.listFiles()[i].getName());
            final File fileItem = currentDir.listFiles()[((FileViewHolder) viewHolder).position];
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
        } else if ( i != 0 && !currentDir.getName().equals( baseDir.getName() )  ) {
            ((FileViewHolder) viewHolder).name.setText(currentDir.listFiles()[i-1].getName());
            final File fileItem = currentDir.listFiles()[((FileViewHolder) viewHolder).position-1];
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

    public void updateDir(File fileItem) {
        Toast.makeText(context, "Directory", Toast.LENGTH_SHORT).show();
        currentDir = fileItem;
        notifyDataSetChanged();
        mBaseListener.onEvent( BaseEvents.DIRECTORY_CHANGED, 0, currentDir);
    }

    @Override
    public int getItemCount() {
        if ( currentDir.getName().equals( baseDir.getName() ) ) {
            return currentDir.listFiles().length;
        } else {
            return currentDir.listFiles().length + 1;
        }
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RelativeLayout RLFile;
        int position;

        public FileViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.fileName);
            RLFile = (RelativeLayout) itemView.findViewById(R.id.RLFile);

        }
    }

}
