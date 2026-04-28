package com.example.inputcontrols;
import android.view.View;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ContextMenu;
import android.widget.PopupMenu;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Button btnSubmit, btnAlert, btnCamera;
    Spinner spinnerCity;
    RatingBar ratingBar;
    SeekBar seekBar;
    TextView tvSeekValue;
    CheckBox cbJava, cbAndroid;
    RadioGroup rgGender;
    Switch switchNotify;
    ToggleButton toggleMode;
    private static final int CAMERA_REQUEST_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS},101);
        }

        // Initialize Views
        etName = findViewById(R.id.etName);
        btnSubmit = findViewById(R.id.btnSubmit);
        spinnerCity = findViewById(R.id.spinnerCity);
        ratingBar = findViewById(R.id.ratingBar);
        seekBar = findViewById(R.id.seekBar);
        tvSeekValue = findViewById(R.id.tvSeekValue);
        btnAlert = findViewById(R.id.btnAlert);
        btnCamera = findViewById(R.id.btnCamera);
        cbJava = findViewById(R.id.cbJava);
        cbAndroid = findViewById(R.id.cbAndroid);
        rgGender = findViewById(R.id.rgGender);
        switchNotify = findViewById(R.id.switchNotify);
        toggleMode = findViewById(R.id.toggleMode);
        Button btnClock = findViewById(R.id.btnClock);
        AnalogClock analogClock = findViewById(R.id.analogClock);
        TextClock textClock = findViewById(R.id.textClock);
        TextView tvContext = findViewById(R.id.tvContext);
        registerForContextMenu(tvContext);

        analogClock.setVisibility(View.GONE);
        textClock.setVisibility(View.GONE);

        // Spinner Data
        String[] cities = {"Select City", "Mumbai", "Pune", "Delhi", "Bangalore"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                cities
        );
        spinnerCity.setAdapter(adapter);

        Button btnPopup = findViewById(R.id.btnPopupMenu);

        btnPopup.setOnClickListener(v -> {

            PopupMenu popup = new PopupMenu(this, v);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {

                if(item.getItemId()==R.id.popup_share){
                    Toast.makeText(this,"Share Clicked",Toast.LENGTH_SHORT).show();
                }

                if(item.getItemId()==R.id.popup_logout){
                    Toast.makeText(this,"Logout Clicked",Toast.LENGTH_SHORT).show();
                }

                return true;
            });

            popup.show();
        });

        Button btnNotify = findViewById(R.id.btnNotification);
        btnNotify.setOnClickListener(v -> {

            NotificationManager manager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            String channelId = "my_channel";

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "My Channel",
                        NotificationManager.IMPORTANCE_DEFAULT);

                manager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, channelId)
                            .setContentTitle("Android Notification")
                            .setContentText("Hello from your app!")
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setAutoCancel(true);

            manager.notify(1, builder.build());

        });
//        btnNotify.setOnClickListener(v -> {
//
//            NotificationManager manager =
//                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//            String channelId = "my_channel";
//
//            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
//                NotificationChannel channel =
//                        new NotificationChannel(channelId,"My Channel",
//                                NotificationManager.IMPORTANCE_DEFAULT);
//
//                manager.createNotificationChannel(channel);
//            }
//
//            NotificationCompat.Builder builder =
//                    new NotificationCompat.Builder(this, channelId)
//                            .setContentTitle("Android Notification")
//                            .setContentText("Hello from your app!")
//                            .setSmallIcon(android.R.drawable.ic_dialog_info)
//                            .setAutoCancel(true);
//
//            manager.notify(1, builder.build());
//        });


        // SeekBar Listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekValue.setText("Seek Value: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Submit Button
        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString();
            String city = spinnerCity.getSelectedItem().toString();
            float rating = ratingBar.getRating();
            int seekValue = seekBar.getProgress();

            String skills = "";
            if (cbJava.isChecked()) skills += "Java ";
            if (cbAndroid.isChecked()) skills += "Android ";

            int selectedGenderId = rgGender.getCheckedRadioButtonId();
            String gender = "";
            if (selectedGenderId != -1) {
                RadioButton selectedRadio = findViewById(selectedGenderId);
                gender = selectedRadio.getText().toString();
            }

            String notifications = switchNotify.isChecked() ? "Enabled" : "Disabled";
            String mode = toggleMode.isChecked() ? "Dark Mode ON" : "Dark Mode OFF";

            Toast.makeText(this,
                    "Name: " + name +
                            "\nCity: " + city +
                            "\nRating: " + rating +
                            "\nSeek Value: " + seekValue +
                            "\nSkills: " + skills +
                            "\nGender: " + gender +
                            "\nNotifications: " + notifications +
                            "\nMode: " + mode,
                    Toast.LENGTH_LONG).show();
        });

        // Alert Dialog Button
        btnAlert.setOnClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Alert Dialog")
                    .setMessage("Program executed successfully")
                    .setPositiveButton("OK", null)
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        // Camera Button
//        btnCamera.setOnClickListener(v -> {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivity(intent);
//        });

        btnClock.setOnClickListener(v -> {

            if (analogClock.getVisibility() == View.VISIBLE) {

                analogClock.setVisibility(View.GONE);
                textClock.setVisibility(View.GONE);
                btnClock.setText("Show Clocks");

            } else {

                analogClock.setVisibility(View.VISIBLE);
                textClock.setVisibility(View.VISIBLE);
                btnClock.setText("Hide Clocks");
            }

        });
        btnCamera.setOnClickListener(v -> {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_REQUEST_CODE);

            } else {
                openCamera();
            }
        });
    }
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.option_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (item.getItemId() == R.id.menu_settings) {
//            Toast.makeText(this,"Settings Selected",Toast.LENGTH_SHORT).show();
//        }
//
//        if (item.getItemId() == R.id.menu_about) {
//            Toast.makeText(this,"About Selected",Toast.LENGTH_SHORT).show();
//        }
//
//        return true;
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.option_menu, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.menu_settings){
            Toast.makeText(this,"Settings Selected",Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()==R.id.menu_about){
            Toast.makeText(this,"About Selected",Toast.LENGTH_SHORT).show();
        }

        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.context_edit){
            Toast.makeText(this,"Edit Clicked",Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()==R.id.context_delete){
            Toast.makeText(this,"Delete Clicked",Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}