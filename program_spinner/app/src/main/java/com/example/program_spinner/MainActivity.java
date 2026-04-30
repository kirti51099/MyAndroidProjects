package com.example.program_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button btn;
    TextView result;

    String[] items = {"Select", "Java", "Android", "Python", "C++"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        btn = findViewById(R.id.btn);
        result = findViewById(R.id.result);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        btn.setOnClickListener(v -> {
            String selected = spinner.getSelectedItem().toString();
            result.setText("Selected: " + selected);
        });
    }
}