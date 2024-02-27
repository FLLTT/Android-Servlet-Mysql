package com.example.homework03;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IbtninfoActivity  extends AppCompatActivity {
    private ImageButton btnhome ,btndiet, btnbasket, btninfo;
    private Button btnChangePassword,btnCheck,btnSubmit,btnOrder;
    private TextView username, phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibtninfo);

        btnhome =findViewById(R.id.imageButtonhome);
        btndiet = findViewById(R.id.imageButtondiet);
        btnbasket = findViewById(R.id.imageButtonbasket);
        btninfo = findViewById(R.id.imageButtoninfo);

        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnCheck = findViewById(R.id.btnCheck);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnOrder = findViewById(R.id.btnOrder);
        username = findViewById(R.id.username);
        phoneNumber = findViewById(R.id.phoneNumber);

        Intent intent = getIntent();
        String pusername = intent.getStringExtra("username");
        String pphoneNumber = intent.getStringExtra("phoneNumber");
        if(pusername != null){
            username.setText("用户名：" + pusername);
            phoneNumber.setText("电话：" + pphoneNumber);
            SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username",pusername);
            editor.putString("phoneNumber",pphoneNumber);
            editor.putBoolean("isLoggedIn",true);
            editor.apply();
        }/*else {
            username.setText("用户名：未登录");
            phoneNumber.setText("电话：未登录" );
        }*/
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "未登录");
        String savedPhoneNumber = sharedPreferences.getString("phoneNumber", "未登录");
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        username.setText("用户名：" + savedUsername);
        phoneNumber.setText("电话：" + savedPhoneNumber);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn){
                    Intent intent = new Intent(IbtninfoActivity.this, OrderCheckActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(IbtninfoActivity.this, "please login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn){
                    Intent intent = new Intent(IbtninfoActivity.this, IbtndietActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(IbtninfoActivity.this, "please login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtninfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtninfoActivity.this, PasswordChangeActivity.class);
                startActivity(intent);
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn){
                    Intent intent = new Intent(IbtninfoActivity.this, IbtnhomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(IbtninfoActivity.this, "please login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn){
                    Intent intent = new Intent(IbtninfoActivity.this, IbtndietActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(IbtninfoActivity.this, "please login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn){
                    Intent intent = new Intent(IbtninfoActivity.this, IbtnbasketActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(IbtninfoActivity.this, "please login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}