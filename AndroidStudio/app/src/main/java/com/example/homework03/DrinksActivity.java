package com.example.homework03;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DrinksActivity extends AppCompatActivity {
    private ImageButton btnhome ,btndiet, btnbasket, btninfo;
    private Button btncommon , btndessert, btndrinks,btndata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        btnhome =findViewById(R.id.imageButtonhome);
        btndiet = findViewById(R.id.imageButtondiet);
        btnbasket = findViewById(R.id.imageButtonbasket);
        btninfo = findViewById(R.id.imageButtoninfo);

        btncommon = findViewById(R.id.btncommon);
        btndessert = findViewById(R.id.btndessert);
        btndrinks = findViewById(R.id.btndrinks);
        btndata =findViewById(R.id.btndatabase);

        btncommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinksActivity.this,IbtndietActivity.class);
                startActivity(intent);
            }
        });
        btndessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinksActivity.this,DessertActivity.class);
                startActivity(intent);
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinksActivity.this, IbtnhomeActivity.class);
                startActivity(intent);
            }
        });

        btnbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinksActivity.this, IbtnbasketActivity.class);
                startActivity(intent);
            }
        });
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinksActivity.this, IbtninfoActivity.class);
                startActivity(intent);
            }
        });
        btndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinksActivity.this, DietDatabaseTextActivity.class);
                startActivity(intent);
            }
        });

    }
}
