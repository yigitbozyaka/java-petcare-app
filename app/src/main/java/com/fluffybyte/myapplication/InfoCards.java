package com.fluffybyte.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class InfoCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cards);

        CardView cardView1 = findViewById(R.id.cardView1);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setVisibility(View.GONE);

        CardView cardView2 = findViewById(R.id.cardView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        textView4.setVisibility(View.GONE);

        CardView cardView3 = findViewById(R.id.cardView3);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        textView6.setVisibility(View.GONE);

        CardView cardView4 = findViewById(R.id.cardView4);
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView8 = findViewById(R.id.textView8);
        textView8.setVisibility(View.GONE);

        CardView cardView5 = findViewById(R.id.cardView5);
        TextView textView9 = findViewById(R.id.textView9);
        TextView textView10 = findViewById(R.id.textView10);
        textView10.setVisibility(View.GONE);

        cardView1.setOnClickListener(v -> {
            if (textView2.getVisibility() == View.GONE) {
                textView2.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.GONE);
            } else {
                textView2.setVisibility(View.GONE);
                textView1.setVisibility(View.VISIBLE);
            }
        });

        cardView2.setOnClickListener(v -> {
            if (textView4.getVisibility() == View.GONE) {
                textView4.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
            } else {
                textView4.setVisibility(View.GONE);
                textView3.setVisibility(View.VISIBLE);
            }
        });

        cardView3.setOnClickListener(v -> {
            if (textView6.getVisibility() == View.GONE) {
                textView6.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.GONE);
            } else {
                textView6.setVisibility(View.GONE);
                textView5.setVisibility(View.VISIBLE);
            }
        });

        cardView4.setOnClickListener(v -> {
            if (textView8.getVisibility() == View.GONE) {
                textView8.setVisibility(View.VISIBLE);
                textView7.setVisibility(View.GONE);
            } else {
                textView8.setVisibility(View.GONE);
                textView7.setVisibility(View.VISIBLE);
            }
        });

        cardView5.setOnClickListener(v -> {
            if (textView10.getVisibility() == View.GONE) {
                textView10.setVisibility(View.VISIBLE);
                textView9.setVisibility(View.GONE);
            } else {
                textView10.setVisibility(View.GONE);
                textView9.setVisibility(View.VISIBLE);
            }
        });

    }

    public void handleHome(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


    public void handlePetCard(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, PetCard.class);
            startActivity(intent);
        }
    }
}