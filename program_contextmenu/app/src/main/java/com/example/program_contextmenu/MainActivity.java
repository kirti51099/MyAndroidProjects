package com.example.program_contextmenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);

        // Register for context menu
        registerForContextMenu(text);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Select Option");
        menu.add(0, 1, 0, "Edit");
        menu.add(0, 2, 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == 1) {
            Toast.makeText(this, "Edit selected", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == 2) {
            Toast.makeText(this, "Delete selected", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}