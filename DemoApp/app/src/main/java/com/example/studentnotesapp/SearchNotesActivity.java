package com.example.studentnotesapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchNotesActivity extends AppCompatActivity {

    EditText searchBar;
    ListView listView;

    ArrayList<String> notesList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_notes);

        searchBar = findViewById(R.id.searchBar);
        listView = findViewById(R.id.notesList);

        // Demo Notes Data
        notesList = new ArrayList<>();
        notesList.add("Java Programming Notes");
        notesList.add("Android UI Design Notes");
        notesList.add("Database Management System");
        notesList.add("Operating System Concepts");
        notesList.add("Software Engineering Notes");

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                notesList
        );

        listView.setAdapter(adapter);

        // Search Function
        searchBar.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
}