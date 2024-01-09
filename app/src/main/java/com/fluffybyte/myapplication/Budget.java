package com.fluffybyte.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Budget extends AppCompatActivity {

    SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        sharedRef = new SharedRef(this);

        CardView cardView1 = findViewById(R.id.foodBudget);
        CardView cardView2 = findViewById(R.id.litterBudget);
        CardView cardView3 = findViewById(R.id.vetCareBudget);
        CardView cardView4 = findViewById(R.id.medBudget);
        CardView cardView5 = findViewById(R.id.groomBudget);
        CardView cardView6 = findViewById(R.id.healthBudget);

        cardView1.setOnClickListener(v -> showEditBudgetDialog("foodBudget"));

        cardView2.setOnClickListener(v -> showEditBudgetDialog("litterBudget"));

        cardView3.setOnClickListener(v -> showEditBudgetDialog("vetCareBudget"));

        cardView4.setOnClickListener(v -> showEditBudgetDialog("medBudget"));

        cardView5.setOnClickListener(v -> showEditBudgetDialog("groomBudget"));

        cardView6.setOnClickListener(v -> showEditBudgetDialog("healthBudget"));

        TextView foodBudgetText = findViewById(R.id.foodBudgetText);
        TextView litterBudgetText = findViewById(R.id.litterBudgetText);
        TextView vetCareBudgetText = findViewById(R.id.vetCareBudgetText);
        TextView medBudgetText = findViewById(R.id.medBudgetText);
        TextView groomBudgetText = findViewById(R.id.groomBudgetText);
        TextView healthBudgetText = findViewById(R.id.healthBudgetText);

        foodBudgetText.setText(loadAndSetBudget("foodBudget"));
        litterBudgetText.setText(loadAndSetBudget("litterBudget"));
        vetCareBudgetText.setText(loadAndSetBudget("vetCareBudget"));
        medBudgetText.setText(loadAndSetBudget("medBudget"));
        groomBudgetText.setText(loadAndSetBudget("groomBudget"));
        healthBudgetText.setText(loadAndSetBudget("healthBudget"));

    }

    private String loadAndSetBudget(String type) {
        String budget = sharedRef.loadData(type);
        if (budget.isEmpty()) {
            budget = "0";
            sharedRef.saveData(type, budget);
        }
        return budget;
    }

    private void showEditBudgetDialog(String type) {
        TextView foodBudgetText = findViewById(R.id.foodBudgetText);
        TextView litterBudgetText = findViewById(R.id.litterBudgetText);
        TextView vetCareBudgetText = findViewById(R.id.vetCareBudgetText);
        TextView medBudgetText = findViewById(R.id.medBudgetText);
        TextView groomBudgetText = findViewById(R.id.groomBudgetText);
        TextView healthBudgetText = findViewById(R.id.healthBudgetText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_budget, null);
        builder.setView(dialogView);

        EditText editTextBudget = dialogView.findViewById(R.id.editTextBudget);
        switch (type) {
            case "foodBudget":
                editTextBudget.setText(foodBudgetText.getText().toString());
                break;
            case "litterBudget":
                editTextBudget.setText(litterBudgetText.getText().toString());
                break;
            case "vetCareBudget":
                editTextBudget.setText(vetCareBudgetText.getText().toString());
                break;
            case "medBudget":
                editTextBudget.setText(medBudgetText.getText().toString());
                break;
            case "groomBudget":
                editTextBudget.setText(groomBudgetText.getText().toString());
                break;
            case "healthBudget":
                editTextBudget.setText(healthBudgetText.getText().toString());
                break;
        }

        builder.setTitle("Set Budget");
        builder.setPositiveButton("OK", (dialog, which) -> {
            String newBudget = editTextBudget.getText().toString();
            switch (type) {
                case "foodBudget":
                    foodBudgetText.setText(newBudget);
                    sharedRef.saveData("foodBudget", newBudget);
                    break;
                case "litterBudget":
                    litterBudgetText.setText(newBudget);
                    sharedRef.saveData("litterBudget", newBudget);
                    break;
                case "vetCareBudget":
                    vetCareBudgetText.setText(newBudget);
                    sharedRef.saveData("vetCareBudget", newBudget);
                    break;
                case "medBudget":
                    medBudgetText.setText(newBudget);
                    sharedRef.saveData("medBudget", newBudget);
                    break;
                case "groomBudget":
                    groomBudgetText.setText(newBudget);
                    sharedRef.saveData("groomBudget", newBudget);
                    break;
                case "healthBudget":
                    healthBudgetText.setText(newBudget);
                    sharedRef.saveData("healthBudget", newBudget);
                    break;
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
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
}