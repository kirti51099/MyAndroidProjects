package com.example.studentnotesapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewNotesActivity extends AppCompatActivity {

    ListView notesList;

    String[] notes = {
            "Java Programming Notes",
            "Android UI Design Notes",
            "Database Management Notes",
            "Operating System Notes"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        notesList = findViewById(R.id.notesList);

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                notes
        );

        notesList.setAdapter(adapter);
    }
}