package com.example.homework03;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button btnRegister , btnSubmit;
    private EditText editTextUsername,editTextPassword;
    private String resultAll;
    private TextView textView4;
    private boolean logJudeg = false; // 用于判断是否登录 作用：修改密码仅限于登录时 密码错误后才可触发 若用户不存在 则注册
    private String result= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.btnRegister);
        btnSubmit = findViewById(R.id.btnSubmit);
        editTextUsername = (EditText)findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);

        //new NetworkTask().execute(); //测试方法

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                //System.out.println(username);
                new SendUsernameTask().execute();

            }
        });

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
            }
    public void changePassword(View view) {
        if(logJudeg){
            Intent intent = new Intent(MainActivity.this, PasswordChangeActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(MainActivity.this, "please log first！", Toast.LENGTH_SHORT).show();
        }

    }
    private class SendUsernameTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String name = editTextUsername.getText().toString();
                String urlString = "http://127.0.0.1/usernameview.do?postData=" + name;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    result = bufferedReader.readLine();
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
            if (s != null) {
                String[] parts = s.split(",");
                String pusername = parts[1];
                String ppassword = parts[2];
                String pphoneNumber = parts[3];
                System.out.println(pusername);
                System.out.println(ppassword);
                if (pusername.equals(editTextUsername.getText().toString())) {
                    if (ppassword.equals(editTextPassword.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, IbtninfoActivity.class);
                        intent.putExtra("username",pusername);
                        intent.putExtra("phoneNumber",pphoneNumber);
                        startActivity(intent);
                    } else {
                        logJudeg = true;
                        Toast.makeText(MainActivity.this, "wrong password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }else  {
                Toast.makeText(MainActivity.this, "user does not exist!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /*private class NetworkTask extends AsyncTask<Void,  Void ,String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            String sUrl = "http://192.168.200.1/userlist.do";
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

        @Override
        protected void onPostExecute(String s) {
            textView4.setText(s);
            resultAll = s ;
        }
    }*/
}