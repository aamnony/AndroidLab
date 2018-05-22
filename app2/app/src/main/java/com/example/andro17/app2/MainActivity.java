package com.example.andro17.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnMagic;
    private EditText txtMagic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this,
                        txtMagic.getText().toString(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private void init() {
        btnMagic = findViewById(R.id.btnMagic);
        txtMagic = findViewById(R.id.txtMagic);
    }
}
