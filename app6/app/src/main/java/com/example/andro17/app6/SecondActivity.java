package com.example.andro17.app6;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private ImageView imgBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        loadImageFromIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadImageFromIntent() {
        String url = getIntent().getStringExtra(MainActivity.BACKGROUND_URL);
        try {
            Bitmap bmp = DrawableUtils.drawableFromUrl(url);
            imgBackground.setImageBitmap(bmp);
        } catch (IOException e) {
            Toast.makeText(
                    this,
                    getString(R.string.error_loading_image, url),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void init() {
        imgBackground = findViewById(R.id.imgBackground);
    }
}
