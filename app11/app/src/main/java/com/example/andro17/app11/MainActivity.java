package com.example.andro17.app11;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ToggleButton btnAnimation;
    private ImageView imgAnimation;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnAnimation = findViewById(R.id.btnAnimation);
        btnAnimation.setOnCheckedChangeListener(this);
        imgAnimation = findViewById(R.id.imgAnimation);
        imgAnimation.setBackgroundResource(R.drawable.animations);
        animationDrawable = (AnimationDrawable) imgAnimation.getBackground();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // Start animation
            animationDrawable.start();
        } else {
            // Stop animation
            animationDrawable.stop();
        }
    }
}
