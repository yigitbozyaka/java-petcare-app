package com.fluffybyte.myapplication;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Calendar;

public class Feeding extends AppCompatActivity {

    private static final String CHANNEL_ID = "FeedingChannel";
    private SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);

        sharedRef = new SharedRef(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            createNotificationChannel();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
        }

        Spinner foodSpinner = findViewById(R.id.foodSpinner);
        ArrayAdapter<CharSequence> foodAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.time_array,
                R.layout.color_spinner_layout
        );
        foodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(foodAdapter);

        foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                scheduleNotification(position, "Food");
                sharedRef.saveData("foodSpinnerPosition", String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        Spinner waterSpinner = findViewById(R.id.waterSpinner);
        ArrayAdapter<CharSequence> waterAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.time_array,
                R.layout.color_spinner_layout
        );
        waterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterSpinner.setAdapter(waterAdapter);

        waterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                scheduleNotification(position, "Water");
                sharedRef.saveData("waterSpinnerPosition", String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        int savedFoodPosition;
        try {
            savedFoodPosition = Integer.parseInt(sharedRef.loadData("foodSpinnerPosition"));
        } catch (NumberFormatException e) {
            savedFoodPosition = 0;
        }
        foodSpinner.setSelection(savedFoodPosition);

        int savedWaterPosition;
        try {
            savedWaterPosition = Integer.parseInt(sharedRef.loadData("waterSpinnerPosition"));
        } catch (NumberFormatException e) {
            savedWaterPosition = 0;
        }
        waterSpinner.setSelection(savedWaterPosition);

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void scheduleNotification(int position, String type) {
        long delay = calculateDelay(position);
        Log.d("DELAY", delay + " " + type);

        Intent notificationIntent = new Intent(this, NotificationReceiver.class);
        notificationIntent.putExtra("type", type);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, pendingIntent);
    }

    private long calculateDelay(int position) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        switch (position) {
            case 0:
                // Once a day
                calendar.set(Calendar.HOUR_OF_DAY, 8);
                break;
            case 1:
                // Twice a day
                if (calendar.get(Calendar.HOUR_OF_DAY) < 8) {
                    calendar.set(Calendar.HOUR_OF_DAY, 8);
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY, 20);
                }
                break;
            case 2:
                // Three times a day
                if (calendar.get(Calendar.HOUR_OF_DAY) < 8) {
                    calendar.set(Calendar.HOUR_OF_DAY, 8);
                } else if (calendar.get(Calendar.HOUR_OF_DAY) < 14) {
                    calendar.set(Calendar.HOUR_OF_DAY, 14);
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY, 20);
                }
                break;
            case 3:
                // Four times a day
                if (calendar.get(Calendar.HOUR_OF_DAY) < 6) {
                    calendar.set(Calendar.HOUR_OF_DAY, 6);
                } else if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
                    calendar.set(Calendar.HOUR_OF_DAY, 12);
                } else if (calendar.get(Calendar.HOUR_OF_DAY) < 18) {
                    calendar.set(Calendar.HOUR_OF_DAY, 18);
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY, 22);
                }
                break;
            default:
                break;
        }

        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTimeInMillis() - System.currentTimeMillis();
    }

    public void handleHome(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    public void handleVaccines(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, Vaccines.class);
            startActivity(intent);
        }
    }
}