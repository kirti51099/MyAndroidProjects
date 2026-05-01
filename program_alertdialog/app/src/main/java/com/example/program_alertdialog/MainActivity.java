package com.example.program_alertdialog;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("Do you want to continue?");

            builder.setPositiveButton("Yes", (dialog, which) -> {
                // action
            });

            builder.setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
            });

            builder.show();
        });
    }
}