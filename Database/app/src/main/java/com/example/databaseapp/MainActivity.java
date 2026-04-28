package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    Button btnInsert, btnView;
    DBHelper db;
    EditText editId;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        editId = findViewById(R.id.editId);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        db = new DBHelper(this);

        // Insert Button
        btnInsert.setOnClickListener(v -> {
            String name = editName.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            } else {
                boolean result = db.insertData(name);
                if (result) {
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // View Button
        btnView.setOnClickListener(v -> {
            Cursor cursor = db.getData();

            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            } else {
                StringBuilder data = new StringBuilder();

                while (cursor.moveToNext()) {
                    data.append("ID: ").append(cursor.getInt(0)).append("\n");
                    data.append("Name: ").append(cursor.getString(1)).append("\n\n");
                }

                Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //Update Button
        btnUpdate.setOnClickListener(v -> {
            String id = editId.getText().toString();
            String name = editName.getText().toString();

            if (id.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Enter ID and Name", Toast.LENGTH_SHORT).show();
            } else {
                boolean result = db.updateData(id, name);
                if (result) {
                    Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(v -> {
            String id = editId.getText().toString();

            if (id.isEmpty()) {
                Toast.makeText(this, "Enter ID", Toast.LENGTH_SHORT).show();
            } else {
                boolean result = db.deleteData(id);
                if (result) {
                    Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
                   
                } else {
                    Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}