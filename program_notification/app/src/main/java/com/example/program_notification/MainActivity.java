package com.example.program_notification;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    String CHANNEL_ID = "my_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

        btn.setOnClickListener(v -> {

            NotificationManager manager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // Create channel (required for Android 8+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel =
                        new NotificationChannel(CHANNEL_ID, "My Channel",
                                NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setContentTitle("Hello")
                            .setContentText("This is a Notification")
                            .setSmallIcon(android.R.drawable.ic_dialog_info);

            manager.notify(1, builder.build());
        });
    }
}