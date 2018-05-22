package com.example.andro17.app6;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.os.StrictMode.*;
import static android.os.StrictMode.setThreadPolicy;

public class MainActivity extends AppCompatActivity {

    public static final String BACKGROUND_URL = "com.example.andro17.app6.MainActivity.BACKGROUND_URL";

    private EditText txtUrl;
    private Button btnTestBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        init();
        btnTestBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(BACKGROUND_URL, txtUrl.getText().toString());
                startActivity(intent);
            }
        });

        txtUrl.setText("http://softlab.technion.ac.il/kulik/background.jpg");
    }

    private void init() {
        txtUrl = findViewById(R.id.txtUrl);
        btnTestBackground = findViewById(R.id.btnTestBackground);
    }
}
