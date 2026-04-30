package com.example.program5_absolutelayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button btn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        btn = findViewById(R.id.btn);
        result = findViewById(R.id.result);

        btn.setOnClickListener(v -> {
            String n = name.getText().toString();
            result.setText("Hello " + n);
        });
    }
}