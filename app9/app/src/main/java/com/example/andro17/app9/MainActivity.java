package com.example.andro17.app9;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_PERMISSION_STORAGE_AND_CAMERA = 100;
    private static final int REQUEST_VIDEO_CAPTURE = 1000;

    private Button btnStartCamera;
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnStartCamera = findViewById(R.id.btnStartCamera);
        btnStartCamera.setOnClickListener(this);
        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_STORAGE_AND_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_VIDEO_CAPTURE:
                if (resultCode == RESULT_OK) {
                    videoView.setVideoURI(data.getData());
                    videoView.setMediaController(mediaController);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_STORAGE_AND_CAMERA);
    }
}
