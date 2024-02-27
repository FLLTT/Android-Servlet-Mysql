package com.example.homework03;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrderCheckActivity  extends AppCompatActivity {
    private Button btnBack;
    private String result = "";
    private TextView textshow;
    private String sfinal= "";
    private int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderinfocheck);

        btnBack = findViewById(R.id.btnBack);
        textshow = findViewById(R.id.textshow);

        new SendUsernameTask().execute();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderCheckActivity.this, IbtninfoActivity.class);
                startActivity(intent);
            }
        });
    }
    private class SendUsernameTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                String savedUsername = sharedPreferences.getString("username", "未登录");
                String savedPhoneNumber = sharedPreferences.getString("phoneNumber", "未登录");

                String urlString = "http://127.0.0.1/orderread.do?username=" + savedUsername + "&phonenumber=" + savedPhoneNumber;
                //String urlString = "http://127.0.0.1/orderread.do?username=admin&phonenumber="
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String inputLine = "";
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while((inputLine = bufferedReader.readLine())!=null){
                        result += inputLine + "\n";
                    }
                    //System.out.println(result);
                }
            } catch (IOException e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            String part[] = s.split("\n");
            for (int i = 0 ; i < part.length;i++){
                String sCounter = count + "";
                sfinal += sCounter + part[i] +"\n";
                count++;
            }
            textshow.setText(sfinal);
        }
    }
}
