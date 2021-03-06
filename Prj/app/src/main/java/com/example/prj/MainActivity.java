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
                      Log.e("?????? ??????", "????????? ????????? ????????????" + str);
                      InputStreamReader isr = new InputStreamReader(http.getInputStream(), "utf8");
                      BufferedReader reader = new BufferedReader(isr);

                      String str2;
                      StringBuilder builder = new StringBuilder();
                      while((str2 = reader.readLine()) != null){
                          builder.append(str2);
                          String resultData = builder.toString();
                          final String[] sResult = resultData.split("/");
                          Log.e("?????? ??????", "???????????? ??? ?????????" + str2);
                          handler.post(new Runnable() {
                              @Override
                              public void run() {
                                 if(id.equals(sResult[0]) && pwd.equals(sResult[2])) {
                                     SessionManager sessionManager = new SessionManager(getApplicationContext());
                                     sessionManager.setId(sResult[0]);
                                     sessionManager.setName(sResult[1]);
                                     sessionManager.setPwd(sResult[2]);

                                     Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                     startActivity(intent);
                                     Toast.makeText(MainActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                                 } else {
                                     Toast.makeText(MainActivity.this, "???????????? ??????????????? ?????? ??????????????????", Toast.LENGTH_SHORT).show();
                                 }
                              }
                          });
                     }
                  } catch (Exception e){
                      Log.e("?????? ??????", "????????? ????????? ?????? ??????");
                  }
              }
        }.start();
    }
}