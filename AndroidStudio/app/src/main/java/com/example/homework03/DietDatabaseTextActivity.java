package com.example.homework03;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DietDatabaseTextActivity extends AppCompatActivity {
    private String result = "";
    private String sendResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dietdatabase);
            new GetDietInfoTask().execute();
    }
    private class GetDietInfoTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String sUrl = "http://192.168.200.1/dietlist.do";
            try{
                URL url = new URL(sUrl);
                String inputLine = "";
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(in);

                while((inputLine = reader.readLine())!=null){
                    result += inputLine + "\n";
                }
                reader.close();
                in.close();
                urlConnection.disconnect();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return result;
        }
        protected void onPostExecute(String s) {
            result = s ;
            //System.out.println(result);
            RelativeLayout rootView = new RelativeLayout(DietDatabaseTextActivity.this);
            rootView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
            ));

            // 导航栏 navigation
            LinearLayout navigationLayout = new LinearLayout(DietDatabaseTextActivity.this);
            navigationLayout.setId(View.generateViewId());
            navigationLayout.setOrientation(LinearLayout.VERTICAL);
            navigationLayout.setBackgroundColor(Color.WHITE);
            navigationLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT
            ));

            rootView.addView(navigationLayout);

            Button btnCommon = new Button(DietDatabaseTextActivity.this);
            btnCommon.setId(View.generateViewId());
            btnCommon.setText("常规菜品");
            navigationLayout.addView(btnCommon);

            Button btnDessert = new Button(DietDatabaseTextActivity.this);
            btnDessert.setId(View.generateViewId());
            btnDessert.setText("甜点小吃");
            navigationLayout.addView(btnDessert);

            Button btnDrinks = new Button(DietDatabaseTextActivity.this);
            btnDrinks.setId(View.generateViewId());
            btnDrinks.setText("饮料酒水");
            navigationLayout.addView(btnDrinks);

            Button btndatabasetext = new Button(DietDatabaseTextActivity.this);
            btndatabasetext.setId(View.generateViewId());
            btndatabasetext.setText("数据测试");
            navigationLayout.addView(btndatabasetext);

            // 添加滚动视图
            ScrollView scrollView = new ScrollView(DietDatabaseTextActivity.this);
            scrollView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
            ));
            RelativeLayout.LayoutParams scrollViewParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
            scrollViewParams.addRule(RelativeLayout.END_OF, navigationLayout.getId());
            rootView.addView(scrollView);

            // 菜单栏
            LinearLayout dishLayout = new LinearLayout(DietDatabaseTextActivity.this);
            dishLayout.setOrientation(LinearLayout.VERTICAL);
            scrollView.addView(dishLayout);


            System.out.println(result);
            String[] dietAllInfo = result.split("\n");
            for(int i = 0 ; i < dietAllInfo.length ; i++){
                String[] dietInfo = dietAllInfo[i].split(",");
                LinearLayout.LayoutParams frameLayoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                frameLayoutParams.setMargins(0, 0, 0, 10);
                FrameLayout dishItemLayout = new FrameLayout(DietDatabaseTextActivity.this);
                dishItemLayout.setLayoutParams(frameLayoutParams);
                dishItemLayout.setBackgroundColor(Color.parseColor("#ECECEC"));
                dishItemLayout.setId(i);
                dishLayout.addView(dishItemLayout);

                ImageView dishImage = new ImageView(DietDatabaseTextActivity.this);
                dishImage.setLayoutParams(new FrameLayout.LayoutParams(150, 150));
                dishImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                String imageName = dietInfo[3]; // 图片文件名
                Class<?> drawableClass = R.drawable.class;
                Field field;
                int resourceId;
                try {
                    field = drawableClass.getField(imageName);
                    resourceId = field.getInt(null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                    resourceId = 0;
                }
                if (resourceId != 0) {
                    dishImage.setImageResource(resourceId);
                } else {
                    System.out.println("can‘t find!");
                }
                dishItemLayout.addView(dishImage);

                LinearLayout containerLayout = new LinearLayout(DietDatabaseTextActivity.this);
                FrameLayout.LayoutParams containerParams = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                );
                containerParams.gravity = Gravity.CENTER_HORIZONTAL;
                containerLayout.setLayoutParams(containerParams);
                containerLayout.setOrientation(LinearLayout.VERTICAL);
                dishItemLayout.addView(containerLayout);

                TextView dishName = new TextView(DietDatabaseTextActivity.this);
                LinearLayout.LayoutParams dishNameParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                dishNameParams.gravity = Gravity.CENTER_HORIZONTAL;
                dishName.setLayoutParams(dishNameParams);
                dishName.setText(dietInfo[1]); // 设置正确的菜名
                dishName.setTextColor(Color.parseColor("#5BA3DC"));
                dishName.setTextSize(16);
                dishName.setTypeface(dishName.getTypeface(), Typeface.BOLD);
                dishName.setId(i);
                containerLayout.addView(dishName);

                TextView dishprice = new TextView(DietDatabaseTextActivity.this);
                LinearLayout.LayoutParams dishpriceParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                dishpriceParams.gravity = Gravity.CENTER_HORIZONTAL;
                dishprice.setLayoutParams(dishpriceParams);
                dishprice.setText("￥"+ dietInfo[2]); // 设置正确的价格
                dishprice.setTextColor(Color.parseColor("#5BA3DC"));
                dishprice.setTextSize(16);
                dishprice.setTypeface(dishprice.getTypeface(), Typeface.BOLD);
                dishprice.setId(i);
                containerLayout.addView(dishprice);

                ImageButton addButton = new ImageButton(DietDatabaseTextActivity.this);
                FrameLayout.LayoutParams addButtonParams = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                addButtonParams.gravity = Gravity.END | Gravity.BOTTOM;
                addButton.setLayoutParams(addButtonParams);
                addButton.setBackgroundColor(Color.TRANSPARENT);
                addButton.setImageResource(android.R.drawable.ic_menu_add);
                addButton.setId(i);
                dishItemLayout.addView(addButton);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendResult +=  dietInfo[1] + ","+ dietInfo[2]+ "\n" ;
                        SharedPreferences sharedPreferences = getSharedPreferences("orderdiet",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isOrderDiet",true);
                    }
                });
            }


        LinearLayout bottomLayout = new LinearLayout(DietDatabaseTextActivity.this);
        LinearLayout.LayoutParams bottomLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bottomLayoutParams.gravity = Gravity.BOTTOM;
        bottomLayout.setLayoutParams(bottomLayoutParams);
        bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
        rootView.addView(bottomLayout);

        ImageButton homeButton = new ImageButton(DietDatabaseTextActivity.this);
        LinearLayout.LayoutParams homeButtonParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT);
        homeButtonParams.gravity = Gravity.BOTTOM;
        homeButtonParams.weight = 1;
        homeButtonParams.width = 100;
        homeButtonParams.height = 100;
        homeButton.setLayoutParams(homeButtonParams);
        homeButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        homeButton.setImageResource(R.drawable.homeimage);
        bottomLayout.addView(homeButton);

        ImageButton dietButton = new ImageButton(DietDatabaseTextActivity.this);
        LinearLayout.LayoutParams dietButtonParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT);
        dietButtonParams.gravity =  Gravity.BOTTOM;
        dietButtonParams.weight = 1;
        dietButtonParams.width = 100;
        dietButtonParams.height = 100;
        dietButton.setLayoutParams(dietButtonParams);
        dietButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        dietButton.setImageResource(R.drawable.nqf);
        bottomLayout.addView(dietButton);

        ImageButton basketButton = new ImageButton(DietDatabaseTextActivity.this);
        LinearLayout.LayoutParams basketButtonParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT);
        basketButtonParams.gravity =  Gravity.BOTTOM;
        basketButtonParams.weight = 1;
        basketButtonParams.width = 100;
        basketButtonParams.height = 100;
        basketButton.setLayoutParams(basketButtonParams);
        basketButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        basketButton.setImageResource(R.drawable.markimg);
        bottomLayout.addView(basketButton);

        ImageButton infoButton = new ImageButton(DietDatabaseTextActivity.this);
        LinearLayout.LayoutParams infoButtonParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT);
        infoButtonParams.gravity =  Gravity.BOTTOM;
        infoButtonParams.weight = 1;
        infoButtonParams.width = 100;
        infoButtonParams.height = 100;
        infoButton.setLayoutParams(infoButtonParams);
        infoButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        infoButton.setImageResource(R.drawable.iimg);
        bottomLayout.addView(infoButton);

            setContentView(rootView);

            btnCommon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietDatabaseTextActivity.this,IbtndietActivity.class);
                    startActivity(intent);
                }
            });
            btnDessert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietDatabaseTextActivity.this,DessertActivity.class);
                    startActivity(intent);
                }
            });
            btnDrinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietDatabaseTextActivity.this,DrinksActivity.class);
                    startActivity(intent);
                }
            });
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietDatabaseTextActivity.this,IbtnhomeActivity.class);
                    startActivity(intent);
                }
            });
            dietButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietDatabaseTextActivity.this,IbtndietActivity.class);
                    startActivity(intent);
                }
            });
            basketButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sendResult.equals("")){
                        Toast.makeText(DietDatabaseTextActivity.this, "You haven't ordered yet!", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(DietDatabaseTextActivity.this,IbtnbasketActivity.class);
                        intent.putExtra("sendResult",sendResult);
                        startActivity(intent);
                    }


                }
            });
            infoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietDatabaseTextActivity.this,IbtninfoActivity.class);
                    startActivity(intent);
                }
            });


        }
    }
}