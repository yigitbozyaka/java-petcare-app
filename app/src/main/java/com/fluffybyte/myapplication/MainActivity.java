package com.fluffybyte.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleVaccines(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, Vaccines.class);
            startActivity(intent);
        }
    }

    public void handleFeeding(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, Feeding.class);
            startActivity(intent);
        }
    }

    public void handleBudget(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, Budget.class);
            startActivity(intent);
        }
    }

    public void handleVets(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, Vets.class);
            startActivity(intent);
        }
    }

    public void handlePetCard(View view){
        if (!isFinishing()){
            Intent intent = new Intent(this, PetCard.class);
            startActivity(intent);
        }
    }

    public void handleInfoCards(View view){
        if (!isFinishing()){
            Intent intent = new Intent(this, InfoCards.class);
            startActivity(intent);
        }
    }

}