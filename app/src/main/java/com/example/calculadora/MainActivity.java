package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btns [] = new Button[10];
        TextView calculatorDisplay = findViewById(R.id.calculator_display);

        for (int i = 0; i < 10; i++) {
            int id = getResources().getIdentifier("button_" + i, "id", getPackageName());
            btns[i] = findViewById(id);
            btns[i].setOnClickListener(this);
        }


        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (btns[i] != null) {
                ids.append(btns[i].getId()).append("\n");
            }
        }

        calculatorDisplay.setText(ids.toString());

    }

    @Override
    public void onClick(View view) {

    }
}