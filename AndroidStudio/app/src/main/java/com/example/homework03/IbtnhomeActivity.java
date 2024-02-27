package com.example.homework03;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IbtnhomeActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;
    private ScrollView scrollView;
    private ImageButton btnhome ,btndiet, btnbasket, btninfo;
    private Button jumpDiet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibtnhome);

        btnhome =findViewById(R.id.imageButtonhome);
        btndiet = findViewById(R.id.imageButtondiet);
        btnbasket = findViewById(R.id.imageButtonbasket);
        btninfo = findViewById(R.id.imageButtoninfo);

        jumpDiet = findViewById(R.id.jumpToDiet);

        btndiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn){
                    Intent intent = new Intent(IbtnhomeActivity.this, IbtndietActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(IbtnhomeActivity.this, "please login first!", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 在延迟后执行的代码
                            Intent loginIntent = new Intent(IbtnhomeActivity.this, IbtninfoActivity.class);
                            startActivity(loginIntent);
                        }
                    }, 1000); // 延迟1秒（1000毫秒）
                }
            }
        });

        btnbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn){
                    Intent intent = new Intent(IbtnhomeActivity.this, IbtnbasketActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(IbtnhomeActivity.this, "please login first!", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 在延迟后执行的代码
                            Intent loginIntent = new Intent(IbtnhomeActivity.this, IbtninfoActivity.class);
                            startActivity(loginIntent);
                        }
                    }, 1000); // 延迟1秒（1000毫秒）
                }
            }
        });
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(IbtnhomeActivity.this, IbtninfoActivity.class);
                    startActivity(intent);
            }
        });

        jumpDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn){
                    Intent intent = new Intent(IbtnhomeActivity.this, IbtndietActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(IbtnhomeActivity.this, "please login first!", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 在延迟后执行的代码
                            Intent loginIntent = new Intent(IbtnhomeActivity.this, IbtninfoActivity.class);
                            startActivity(loginIntent);
                        }
                    }, 1000); // 延迟1秒（1000毫秒）
                }

            }
        });

        scrollView = findViewById(R.id.scrollView);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                // 获取当前滚动位置
                int scrollY = scrollView.getScrollY();

                // 计算下一个滚动位置
                int nextScrollY = scrollY + 1000;

                // 执行滚动动画
                ObjectAnimator animator = ObjectAnimator.ofInt(scrollView, "scrollY", nextScrollY);
                animator.setDuration(2000);
                animator.start();

                // 设置延迟时间，控制滚动间隔
                mHandler.postDelayed(this, 1000);
            }

        };
    }
  @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable,2000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }
}







