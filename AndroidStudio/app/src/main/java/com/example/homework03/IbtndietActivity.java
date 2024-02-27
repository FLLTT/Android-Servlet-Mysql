package com.example.homework03;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class IbtndietActivity extends AppCompatActivity {
    private ImageButton btnhome ,btndiet, btnbasket, btninfo;
    private Button btncommon , btndessert, btndrinks, btndata;
    private ImageButton  addButton, addButton1;
    private ImageView dishImage, dishImage1;
    private TextView dishName, dishName1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibtndiet);

        btnhome = findViewById(R.id.imageButtonhome);
        btndiet = findViewById(R.id.imageButtondiet);
        btnbasket = findViewById(R.id.imageButtonbasket);
        btninfo = findViewById(R.id.imageButtoninfo);

        btncommon = findViewById(R.id.btncommon);
        btndessert = findViewById(R.id.btndessert);
        btndrinks = findViewById(R.id.btndrinks);
        btndata = findViewById(R.id.btndatabase);

        /*addButton = findViewById(R.id.addButton);
        dishImage = findViewById(R.id.dishImage);
        dishName = findViewById(R.id.dishName);

        addButton1 = findViewById(R.id.addButton1);
        dishImage1 = findViewById(R.id.dishImage1);
        dishName1 = findViewById(R.id.dishName1);*/

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtndietActivity.this, IbtnhomeActivity.class);
                startActivity(intent);
            }
        });

        btnbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtndietActivity.this, IbtnbasketActivity.class);
                startActivity(intent);
            }
        });
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtndietActivity.this, IbtninfoActivity.class);
                startActivity(intent);
            }
        });

        btndessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtndietActivity.this, DessertActivity.class);
                startActivity(intent);
            }
        });
        btndrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtndietActivity.this, DrinksActivity.class);
                startActivity(intent);
            }
        });

        /*addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtndietActivity.this,IbtnbasketActivity.class);
                intent.putExtra("dietname","爆香红烧肉");
                intent.putExtra("price",48);
                startActivity(intent);
            }
        });
        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtndietActivity.this,IbtnbasketActivity.class);
                intent.putExtra("dietname","凉拌嫩豆腐");
                intent.putExtra("price",28);
                startActivity(intent);
            }
        });*/
        btndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtndietActivity.this, DietDatabaseTextActivity.class);
                startActivity(intent);
            }
        });
    }
}
