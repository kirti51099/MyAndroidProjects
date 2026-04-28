package com.example.filehandlingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    Button btnCreate, btnWrite, btnAppend, btnRead, btnDelete;

    String filename = "demo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        btnCreate = findViewById(R.id.btnCreate);
        btnWrite = findViewById(R.id.btnWrite);
        btnAppend = findViewById(R.id.btnAppend);
        btnRead = findViewById(R.id.btnRead);
        btnDelete = findViewById(R.id.btnDelete);

        btnCreate.setOnClickListener(v -> createFile());
        btnWrite.setOnClickListener(v -> writeFile());
        btnAppend.setOnClickListener(v -> appendFile());
        btnRead.setOnClickListener(v -> readFile());
        btnDelete.setOnClickListener(v -> deleteFile());
    }

    private void createFile() {
        try {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            fos.close();
            Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFile() {
        String text = editText.getText().toString();

        try {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "Data Written", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void appendFile() {
        String text = editText.getText().toString();

        try {
            FileOutputStream fos = openFileOutput(filename, MODE_APPEND);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "Data Appended", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        try {
            FileInputStream fis = openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            textView.setText(builder.toString());
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFile() {

        File file = getFileStreamPath(filename);

        if (file.exists()) {
            file.delete();
            Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show();
            textView.setText("");
        } else {
            Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
        }
    }
}