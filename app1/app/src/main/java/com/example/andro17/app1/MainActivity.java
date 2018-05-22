package com.example.andro17.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnClear;
    private Button btnMagic;
    private EditText txtMagic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMagic.setText("");
                btnMagic.setText(R.string.magic_button_default_text);
            }
        });

        btnMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txtMagic.getText().toString();
                if (text.isEmpty()) {
                    btnMagic.setText(R.string.magic_button_default_text);
                } else {
                    btnMagic.setText(text);
                }
            }
        });
    }

    private void init() {
        btnClear = findViewById(R.id.btnClear);
        btnMagic = findViewById(R.id.btnMagic);
        txtMagic = findViewById(R.id.txtMagic);
    }
}
