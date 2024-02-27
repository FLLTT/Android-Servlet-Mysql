package com.example.homework03;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity  extends AppCompatActivity {
    private EditText editTextAccount;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextPhoneNumber;
    private RadioGroup radioGroupGender;
    private Button btnSubmit;
    private RadioButton radioButton;
    private String result="";
    private String username , password, confirmPassword, phoneNumber, gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextAccount = findViewById(R.id.editTextAccount);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextPhoneNumber = findViewById(R.id.editPhoneNumber);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTextAccount.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();
                confirmPassword = editTextConfirmPassword.getText().toString().trim();
                phoneNumber = editTextPhoneNumber.getText().toString().trim();
                gender = getSelectedGender();
                /*System.out.println(username);
                System.out.println(password);
                System.out.println(phoneNumber);
                System.out.println(gender);*/
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please complete the registration information!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 检查密码和确认密码是否一致
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Two passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 将用户信息存入数据库
                new NetworkTask().execute();

                //注册完成跳转回到登录界面
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // 获取选中的性别
    private String getSelectedGender() {
        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);
        if (radioButton != null) {
            return radioButton.getText().toString();
        }
        return "";
    }
    public class NetworkTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String urlString = "http://127.0.0.1/save.do?username=" + username + "&password=" + password + "&phonenumber=" + phoneNumber + "&gender=" + gender ;
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
            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    /*private class SaveUserInfoTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String sname = editTextAccount.getText().toString().trim();
            String spassword = editTextPassword.getText().toString().trim();
            String spNumber = editTextPhoneNumber.getText().toString().trim();
            String sgender = getSelectedGender();
            try {
                String urlString = "http://127.0.0.1/save.do?username=" + sname + "&password=" + spassword + "&phonenumber" + spNumber + "&gender" + sgender ;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
            }catch (IOException e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
            return null;
        }

    }
*/
}
