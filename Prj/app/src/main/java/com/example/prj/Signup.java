package com.example.prj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup extends AppCompatActivity {
    Handler handler;
    EditText eTId, eTUname, eTPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        handler =  new Handler();

        eTId = findViewById(R.id.eTId);
        eTUname = findViewById(R.id.eTUname);
        eTPwd = findViewById(R.id.eTPwd);

        findViewById(R.id.btnSignUpVerify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = eTId.getText().toString().trim();
                String uname = eTUname.getText().toString().trim();
                String pwd = eTPwd.getText().toString().trim();
                if(isStringEmpty(id, uname, pwd) == false) {
                    String sql = id + "/" + uname + "/" + pwd;
                    dataInsert(sql);
                    Toast.makeText(Signup.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else if(idStringEmpty(id) == true) {
                    Toast.makeText(Signup.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(unameStringEmpty(uname) == true) {
                    Toast.makeText(Signup.this, "사용자명을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(pwdStringEmpty(pwd) == true) {
                    Toast.makeText(Signup.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public void dataInsert(String sql){
        new Thread(){
            public void run(){
                try{
                    URL setURL = new URL("Http://10.0.2.2/insert.php/");
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
    static boolean isStringEmpty(String id, String uname, String pwd) {
        return (id == null || id.isEmpty()) || (uname == null || uname.isEmpty()) || (pwd == null || pwd.isEmpty());
    }
    static boolean idStringEmpty(String id) {
        return id == null || id.isEmpty();
    }
    static boolean unameStringEmpty(String uname) {
        return uname == null || uname.isEmpty();
    }
    static boolean pwdStringEmpty(String pwd) {
        return pwd == null || pwd.isEmpty();
    }
}
