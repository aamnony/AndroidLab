package com.example.andro17.app5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtPrompt;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnFinish;

    private double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        loadValueFromIntent();

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
    }

    private void loadValueFromIntent() {
        value = getIntent().getDoubleExtra(MainActivity.VALUE, 0);
        updatePrompt();
    }

    private void updatePrompt() {
        txtPrompt.setText(getString(R.string.current_value, value));
    }

    private void init() {
        txtPrompt = findViewById(R.id.txtPrompt);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnFinish = findViewById(R.id.btnFinish);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlus:
                incrementValue();
                break;
            case R.id.btnMinus:
                decrementValue();
                break;
            case R.id.btnFinish:
                finishActivity();
                break;
        }
    }

    private void finishActivity() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.VALUE, value);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void decrementValue() {
        value--;
        updatePrompt();
    }

    private void incrementValue() {
        value++;
        updatePrompt();
    }
}
