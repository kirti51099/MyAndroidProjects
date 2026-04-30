package com.example.program_listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String[] items = {
            "Apple", "Banana", "Mango", "Orange",
            "Grapes", "Pineapple", "Cherry"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
        );

        listView.setAdapter(adapter);

        // Click event
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "You selected: " + items[position], Toast.LENGTH_SHORT).show();
        });
    }
}