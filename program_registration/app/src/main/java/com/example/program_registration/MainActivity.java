package com.example.program_registration;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name;
    RadioGroup radioGroup;
    CheckBox cb1, cb2;
    ToggleButton toggle;
    Button btn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        radioGroup = findViewById(R.id.radioGroup);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        toggle = findViewById(R.id.toggle);
        btn = findViewById(R.id.btn);
        result = findViewById(R.id.result);

        btn.setOnClickListener(v -> {

            String n = name.getText().toString();

            String gender = "Not selected";
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton rb = findViewById(selectedId);
                gender = rb.getText().toString();
            }

            String hobbies = "";
            if (cb1.isChecked()) hobbies += "Reading ";
            if (cb2.isChecked()) hobbies += "Sports ";

            String status = toggle.isChecked() ? "ON" : "OFF";

            result.setText("Name: " + n +
                    "\nGender: " + gender +
                    "\nHobbies: " + hobbies +
                    "\nToggle: " + status);
        });
    }
}