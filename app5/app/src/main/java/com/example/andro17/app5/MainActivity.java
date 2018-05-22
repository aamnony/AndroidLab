package com.example.andro17.app5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CALCULATE = 7753;
    public static final String VALUE = "com.example.andro17.app5.MainActivity.value";

    private TextView txtPrompt;
    private EditText txtValue;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueString = txtValue.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(VALUE, Double.parseDouble(valueString));
                startActivityForResult(intent, REQUEST_CODE_CALCULATE);

            }
        });
        txtValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if (str.isEmpty()) {
                    btnCalculate.setEnabled(false);
                } else {
                    btnCalculate.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_CALCULATE:
                if (resultCode == RESULT_OK) {
                    double newValue = data.getDoubleExtra(VALUE, 0);
                    txtPrompt.setText(getString(R.string.current_value, newValue));
                    btnCalculate.setEnabled(false);
                }
                break;
        }
    }

    private void init() {
        txtPrompt = findViewById(R.id.txtPrompt);
        txtValue = findViewById(R.id.txtValue);
        btnCalculate = findViewById(R.id.btnCalculate);
    }
}
