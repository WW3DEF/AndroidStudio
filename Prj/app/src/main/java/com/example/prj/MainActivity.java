package com.example.prj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText eTId, eTPwd;
    Button btnLogin, btnSignUp;
    String signId = getIntent().getStringExtra("id");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTId = (EditText) findViewById(R.id.eTId);
        eTPwd = (EditText) findViewById(R.id.eTPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        eTId.setText(signId);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            }
        });
    }
    public void dataInsert(String sql){
        new Thread(){
            public void run(){
                try{
                    URL setURL = new URL("Http://10.0.2.2/usersearch.php/");
                    HttpURLConnection http = (HttpURLConnection) setURL.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(sql);
                    OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(),"UTF-8");
                    outStream.write(buffer.toString());
                    outStream.flush();


                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");

                    String str;
                    str = buffer.toString();
                    Log.e("",str);
                    final BufferedReader reader = new BufferedReader(tmp);
                    str = "";
                    String stemp;
                    while((stemp= reader.readLine()) != null){
                        System.out.println("reader : "+reader.toString());
                        str += stemp;
                    }
                    System.out.println("결과 : "+str);
                } catch(Exception e){
                    e.printStackTrace();
                    Log.e("dataInsert()","지정 에러 발생", e);
                }
            }
        }.start();
    }
}