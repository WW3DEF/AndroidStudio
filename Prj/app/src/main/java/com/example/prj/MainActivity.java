package com.example.prj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText eTId, eTPwd;
    Button btnLogin, btnSignUp;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTId = (EditText) findViewById(R.id.eTId);
        eTPwd = (EditText) findViewById(R.id.eTPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = eTId.getText().toString().trim();
                dataSearch(id);
            }
        });
    }
    public void dataSearch(String id){
          new Thread(){
              public void run(){
                  try{
                      String pwd = eTPwd.getText().toString().trim();
                      URL url = new URL("http://10.0.2.2/usersearch.php");
                      HttpURLConnection http;
                      http = (HttpURLConnection)url.openConnection();

                      http.setDefaultUseCaches(false);
                      http.setDoInput(true);
                      http.setRequestMethod("POST");
                      http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                      StringBuffer buffer = new StringBuffer();
                      buffer.append("name").append("=").append(id);

                      String str;
                      str = buffer.toString();
                      OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf8");
                      osw.write(str);
                      osw.flush();
                      Log.e("검색 모듈", "서버로 데이터 전달완료" + str);
                      InputStreamReader isr = new InputStreamReader(http.getInputStream(), "utf8");
                      BufferedReader reader = new BufferedReader(isr);

                      String str2;
                      StringBuilder builder = new StringBuilder();
                      while((str2 = reader.readLine()) != null){
                          builder.append(str2);
                          String resultData = builder.toString();
                          final String[] sResult = resultData.split("/");
                          Log.e("검색 모듈", "서버에서 온 데이터" + str2);
                          handler.post(new Runnable() {
                              @Override
                              public void run() {
                                 if(id.equals(sResult[0]) && pwd.equals(sResult[2])) {
                                     Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                     intent.putExtra("id", sResult[0]);
                                     intent.putExtra("name", sResult[1]);
                                     startActivity(intent);
                                     Toast.makeText(MainActivity.this, "로그인했습니다", Toast.LENGTH_SHORT).show();
                                 } else {
                                     Toast.makeText(MainActivity.this, "아이디나 비밀번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                                 }
                              }
                          });
                     }
                  } catch (Exception e){
                      Log.e("검색 모듈", "서버로 데이터 전달 실패");
                  }
              }
        }.start();
    }
}