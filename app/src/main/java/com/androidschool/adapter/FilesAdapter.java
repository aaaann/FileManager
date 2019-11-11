package com.androidschool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidschool.filemanager.R;

import java.util.ArrayList;
import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.FileViewHolder> {

    private List<String> mFileNames;

    public FilesAdapter(List<String> fileNames) {
        mFileNames = new ArrayList<>(fileNames);
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_name_item, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        String name = mFileNames.get(position);
        holder.bind(name);
    }

    @Override
    public int getItemCount() {
        return mFileNames.size();
    }

    static class FileViewHolder extends RecyclerView.ViewHolder {

        private TextView mFileName;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            mFileName = (TextView) itemView;
        }

        private void bind(String name) {
            mFileName.setText(name);
        }
    }

}
