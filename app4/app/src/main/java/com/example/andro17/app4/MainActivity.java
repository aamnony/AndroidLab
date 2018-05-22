package com.example.andro17.app4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import static android.os.StrictMode.*;

public class MainActivity extends AppCompatActivity {

    public static final String IMAGE_ON_URL = "http://softlab.technion.ac.il/kulik/on.png";
    public static final String IMAGE_OFF_URL = "http://softlab.technion.ac.il/kulik/off.png";

    private ImageView image;
    private Button btnToggleImage;
    private boolean isOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        init();
        btnToggleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOn = !isOn;
                updateImage();
            }
        });
    }

    private void init() {
        btnToggleImage = findViewById(R.id.btnToggleImage);
        image = findViewById(R.id.image);

        isOn = false;
        updateImage();
    }

    private void updateImage() {
        String imageUrl = isOn ? IMAGE_ON_URL : IMAGE_OFF_URL;
        try {
            image.setImageBitmap(DrawableUtils.drawableFromUrl(imageUrl));
        } catch (IOException e) {
            Toast.makeText(
                    this,
                    getString(R.string.error_loading_image, imageUrl),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
