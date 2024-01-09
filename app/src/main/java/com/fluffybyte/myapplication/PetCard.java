package com.fluffybyte.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PetCard extends AppCompatActivity {

    private SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_card);

        sharedRef = new SharedRef(this);

        if (sharedRef.loadData("petName") != null || !sharedRef.loadData("petName").isEmpty() &&
                sharedRef.loadData("petBreed") != null || !sharedRef.loadData("petBreed").isEmpty() &&
                sharedRef.loadData("petAge") != null || !sharedRef.loadData("petAge").isEmpty()) {
            TextView name = findViewById(R.id.petName);
            name.setText(sharedRef.loadData("petName"));
            TextView breed = findViewById(R.id.petBreed);
            breed.setText(sharedRef.loadData("petBreed"));
            TextView age = findViewById(R.id.petAge);
            age.setText(sharedRef.loadData("petAge"));
        }
    }

    public void handleCardDetails(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.dialog_edit_card, null);
        EditText name = view1.findViewById(R.id.editTextName);
        EditText breed = view1.findViewById(R.id.editTextBreed);
        EditText age = view1.findViewById(R.id.editTextAge);
        if (!sharedRef.loadData("petName").isEmpty()){
            name.setText(sharedRef.loadData("petName"));
        }
        if (!sharedRef.loadData("petBreed").isEmpty()){
            breed.setText(sharedRef.loadData("petBreed"));
        }
        if (!sharedRef.loadData("petAge").isEmpty()){
            age.setText(sharedRef.loadData("petAge"));
        }
        alertDialog.setView(view1);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Save", (dialog, which) -> {
            sharedRef.saveData("petName", name.getText().toString());
            sharedRef.saveData("petBreed", breed.getText().toString());
            sharedRef.saveData("petAge", age.getText().toString());
            TextView name1 = findViewById(R.id.petName);
            name1.setText(sharedRef.loadData("petName"));
            TextView breed1 = findViewById(R.id.petBreed);
            breed1.setText(sharedRef.loadData("petBreed"));
            TextView age1 = findViewById(R.id.petAge);
            age1.setText(sharedRef.loadData("petAge"));
        });
        alertDialog.setNegativeButton("Cancel", null);
        alertDialog.show();

    }


    public void handleHome(View view) {
        if (!isFinishing()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void handleInfoCards(View view) {
        if (!isFinishing()){
            Intent intent = new Intent(this, InfoCards.class);
            startActivity(intent);
        }
    }
}