package com.akshayltc.chime.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akshayltc.chime.R;

import java.io.File;

public class FileBrowserAdapter extends RecyclerView.Adapter {

    private File currentDir;
    private Context context;


    public FileBrowserAdapter (File currentDir, Context context) {
        this.currentDir = currentDir;
        this.context = context;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FileViewHolder(LayoutInflater.from(context).inflate(R.layout.file_browser_directory_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((FileViewHolder) viewHolder).name.setText(currentDir.listFiles()[i].getName());
    }

    @Override
    public int getItemCount() {
        return currentDir.listFiles().length;
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        public FileViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.fileName);
        }
    }

}
