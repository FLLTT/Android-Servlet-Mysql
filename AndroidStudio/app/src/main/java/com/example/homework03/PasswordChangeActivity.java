package com.example.homework03;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PasswordChangeActivity extends AppCompatActivity {
    private EditText editTextAccount , editTextPhoneNumber, editTextPassword,editTextConfirmPassword;
    private Button btnSubmit;
    private String username , password, confirmPassword, phoneNumber, gender;
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);

        editTextAccount = findViewById(R.id.editTextAccount);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editTextAccount.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();
                confirmPassword = editTextConfirmPassword.getText().toString().trim();
                phoneNumber = editTextPhoneNumber.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(PasswordChangeActivity.this, "Please complete the registration information!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 检查密码和确认密码是否一致
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(PasswordChangeActivity.this, "Two passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 将用户信息存入数据库
                new ChangePasswordTask().execute();

                //注册完成跳转回到登录界面
                Intent intent = new Intent(PasswordChangeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private class ChangePasswordTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                String urlString = "http://127.0.0.1/change.do?username=" + username + "&password=" + password + "&phonenumber=" + phoneNumber  ;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                System.out.println(connection.getResponseCode());
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    result = bufferedReader.readLine();
                    System.out.println(result);
                }
            }catch (IOException e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(PasswordChangeActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}