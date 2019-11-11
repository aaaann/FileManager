package com.androidschool.filemanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.androidschool.adapter.FilesAdapter;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final int EXT_STORAGE_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            initRecyclerView();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXT_STORAGE_REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == EXT_STORAGE_REQ_CODE) {
            int grantResultsLength = grantResults.length;
            if (grantResultsLength > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initRecyclerView();
                Toast.makeText(getApplicationContext(), "You grant read external storage permission.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "You denied reac external storage permission.", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void initRecyclerView() {
        if (isExternalStorageReadable()) {
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            FilesAdapter adapter = new FilesAdapter(Arrays.asList(getFiles()));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(adapter);
        }
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    private String[] getFiles() {
        File dir = Environment.getExternalStorageDirectory();
        return dir != null ? dir.list() : new String[0];
    }
}
