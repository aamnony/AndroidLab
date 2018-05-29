package com.example.andro17.app10;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_PERMISSION_STORAGE_AND_CAMERA = 100;
    private static final int REQUEST_CAMERA = 1000;
    private static final String TAG = MainActivity.class.getName();

    private Uri imgFileUri;
    private TextView txtInfo;
    private ImageView imgCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        createImageFile();
        requestPermissions();
    }

    private void createImageFile() {
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "Exp10Camera"
        );
        mediaStorageDir.mkdir();

        String imgFileName = "IMG_"
                + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date())
                + ".jpg";
        File imgFile = new File(mediaStorageDir, imgFileName);
        imgFileUri = Uri.fromFile(imgFile);

        Log.v(TAG, "Created images directory");
    }

    private void init() {
        txtInfo = findViewById(R.id.txtInfo);
        imgCamera = findViewById(R.id.imgCamera);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_STORAGE_AND_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imgFileUri);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), imgFileUri);
                        imgCamera.setImageBitmap(bmp);
                        txtInfo.setText(getString(
                                R.string.bitmap_information,
                                bmp.getByteCount(),
                                bmp.getWidth(),
                                bmp.getHeight()
                        ));
                        Toast.makeText(
                                this,
                                getString(R.string.file_saved, imgFileUri),
                                Toast.LENGTH_SHORT
                        ).show();
                    } catch (IOException e) {
                        Log.v(TAG, e.getMessage());
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    Log.v(TAG, "Image capturing was canceled");
                } else {
                    Log.v(TAG, "Unknown resultCode " + resultCode);
                }
                break;
        }
    }

    private void requestPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.RECORD_AUDIO,
        };
        ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_STORAGE_AND_CAMERA);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
