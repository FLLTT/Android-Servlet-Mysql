package com.example.homework03;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.crypto.IllegalBlockSizeException;

public class IbtnbasketActivity  extends AppCompatActivity {

    private ImageButton btnhome ,btndiet, btnbasket, btninfo;
    private LinearLayout dishLayout;
    private ScrollView scrollView;
    private double totalAmount = 0.0;
    private TextView totalTextView;
    private String result;
    private String diettotalInfo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibtnbasket);

       /* SharedPreferences sharedPreferences = getSharedPreferences("orderdiet", MODE_PRIVATE); // 判断是否已经点菜
        boolean isOrderDiet = sharedPreferences.getBoolean("isOrderDiet", false);
        if(!isOrderDiet){
            Toast.makeText(IbtnbasketActivity.this, "please order at first!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IbtnbasketActivity.this,IbtndietActivity.class);
            startActivity(intent);
        }*/

        btnhome =findViewById(R.id.imageButtonhome);
        btndiet = findViewById(R.id.imageButtondiet);
        btnbasket = findViewById(R.id.imageButtonbasket);
        btninfo = findViewById(R.id.imageButtoninfo);
        dishLayout = findViewById(R.id.dishLayout);
        scrollView = findViewById(R.id.scrollView);

        Intent intent = getIntent();
        String sendResult = intent.getStringExtra("sendResult");
        String[] dietInfoAll = sendResult.split("\n");

        for (int i = 0; i < dietInfoAll.length ; i++){
            String[] dietInfo = dietInfoAll[i].split(",");
            LinearLayout infoLayout = new LinearLayout(this);
            infoLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            infoLayout.setOrientation(LinearLayout.HORIZONTAL);

            ImageButton removeButton = new ImageButton(this);

            TextView dietNameTextView = new TextView(this);
            TextView priceTextView = new TextView(this);
            String dietname = dietInfo[0];
            String price = dietInfo[1];
            double itemPrice = Double.parseDouble(price);
            String itemName = dietname;

            dietNameTextView.setText("菜名： " + dietname);
            priceTextView.setText("    价格： " + price);
            float fontSize =17; // 字体大小，单位为sp
            dietNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
            priceTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
            infoLayout.addView(dietNameTextView);
            infoLayout.addView(priceTextView);
            infoLayout.addView(removeButton);
            dishLayout.addView(infoLayout);
            final int currentRow = i;
            removeButton.setImageResource(android.R.drawable.ic_delete);
            removeButton.setBackgroundColor(Color.BLACK);
            removeButton.setScaleType(ImageView.ScaleType.CENTER);
            removeButton.setPadding(0, 0, 20, 0);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dishLayout.removeViewAt(currentRow);
                    totalAmount -= itemPrice;
                    totalTextView.setText("合计： " + totalAmount);

                    diettotalInfo = diettotalInfo.replace(itemName,"");
                }
            });
            totalAmount += itemPrice;
            diettotalInfo = diettotalInfo + dietname +"," ;
        }
        LinearLayout summaryLayout = new LinearLayout(this);
        summaryLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        summaryLayout.setOrientation(LinearLayout.HORIZONTAL);
        summaryLayout.setGravity(Gravity.END);

        totalTextView = new TextView(this);
        totalTextView.setText("合计： " + totalAmount);
        float fontSize = 20;
        totalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        summaryLayout.addView(totalTextView);


        Button checkoutButton = new Button(this);
        checkoutButton.setBackgroundColor(Color.GREEN);
        checkoutButton.setText("结算");
        summaryLayout.addView(checkoutButton);
        dishLayout.addView(summaryLayout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform checkout operation here
            }
        });
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtnbasketActivity.this, IbtnhomeActivity.class);
                startActivity(intent);
            }
        });

        btndiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtnbasketActivity.this, IbtndietActivity.class);
                startActivity(intent);
            }
        });
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IbtnbasketActivity.this, IbtninfoActivity.class);
                startActivity(intent);
            }
        });
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IbtnbasketActivity.this);
                builder.setTitle("支付界面");

                // 设置对话框中的自定义布局
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_latout, null);
                ImageView imageView = dialogView.findViewById(R.id.imageView);
                TextView hyperlinkTextView = dialogView.findViewById(R.id.hyperlinkTextView);


                imageView.setImageResource(R.drawable.wechatpay); // 替换为你的图片资源
                hyperlinkTextView.setTextColor(Color.BLUE);
                hyperlinkTextView.bringToFront();
                hyperlinkTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder successBuilder = new AlertDialog.Builder(IbtnbasketActivity.this);
                        successBuilder.setMessage("支付成功");
                        successBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new OrderSaveTask().execute();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 在延迟后执行的代码
                                        Intent loginIntent = new Intent(IbtnbasketActivity.this, IbtninfoActivity.class);
                                        startActivity(loginIntent);
                                    }
                                }, 1000); // 延迟1秒（1000毫秒）
                            }
                        });
                        successBuilder.show();
                    }
                });

                builder.setView(dialogView);

                AlertDialog dialog = builder.create();
                dialog.show();

                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                if (window != null) {
                    layoutParams.copyFrom(window.getAttributes());
                    layoutParams.gravity = Gravity.BOTTOM | Gravity.END; // 将超链接定位到右下角
                    window.setAttributes(layoutParams);
                }
            }
        });
    }
    public class OrderSaveTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            String savedUsername = sharedPreferences.getString("username", "未登录");
            String savedPhoneNumber = sharedPreferences.getString("phoneNumber", "未登录");
            try {
                String urlString = "http://127.0.0.1/ordersave.do?username=" + savedUsername + "&phonenumber=" + savedPhoneNumber + "&dietinfo=" + diettotalInfo + "&totalprice=" + totalAmount ;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                System.out.println(connection.getResponseCode());
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    result = bufferedReader.readLine();
                    //System.out.println(result);
                }
            }catch (IOException e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(IbtnbasketActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

}
