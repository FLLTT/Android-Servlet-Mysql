package com.example.homework03;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class  WelcomeActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        clearLoginInfo(); // 在应用程序启动时清除SharedPreferences中的登录信息
        clearorderdietInfo(); // 同理
        imageView = findViewById(R.id.ivwelcome);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, IbtnhomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500); // 0.5秒延迟时间
    }
    private void clearLoginInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
    private void clearorderdietInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("orderdiet", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}