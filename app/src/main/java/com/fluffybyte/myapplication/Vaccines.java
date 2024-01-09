package com.fluffybyte.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Vaccines extends AppCompatActivity {

    private SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines);

        sharedRef = new SharedRef(this);

        initDatePicker(R.id.datePickerLayout1, R.id.datePicker1);
        initDatePicker(R.id.datePickerLayout2, R.id.datePicker2);
        initDatePicker(R.id.datePickerLayout3, R.id.datePicker3);
        initDatePicker(R.id.datePickerLayout4, R.id.datePicker4);
        initDatePicker(R.id.datePickerLayout5, R.id.datePicker5);
        initDatePicker(R.id.datePickerLayout6, R.id.datePicker6);
    }

    public void handleHome(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void handleFeeding(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, Feeding.class);
            startActivity(intent);
        }
    }

    private void initDatePicker(int layoutId, int editTextId) {
        TextInputLayout datePickerLayout = findViewById(layoutId);
        TextInputEditText nameEditText = findViewById(editTextId);

        String savedDate = sharedRef.loadData(String.valueOf(layoutId));
        if (!savedDate.isEmpty()) {
            nameEditText.setText(savedDate);
        }

        nameEditText.setFocusable(false);
        nameEditText.setOnClickListener(view -> {
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date(selection));
                datePickerLayout.getEditText().setText(date);

                sharedRef.saveData(String.valueOf(layoutId), date);
            });
            materialDatePicker.show(getSupportFragmentManager(), "tag");
        });
    }

}