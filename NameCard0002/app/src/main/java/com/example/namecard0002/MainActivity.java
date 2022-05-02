package com.example.namecard0002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText inputName, inputTelNo, inputEmail;
    Button btnSave, btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = (EditText) findViewById(R.id.inputName);
        inputTelNo = (EditText) findViewById(R.id.inputTelNo);
        inputEmail = (EditText) findViewById(R.id.inputEmail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataInsert();
                System.out.println("");
            }
        });
    }
    public void dataInsert() {
        new Thread() {
            public void run(){
                String tempName, tempTelNo, tempEmail;
                tempName = inputName.getText().toString();
                tempTelNo = inputTelNo.getText().toString();
                tempEmail = inputEmail.getText().toString();
                String emulatorIP = "http://10.0.2.2/";
                String smartPhoneIP = "real IP address";
                try {
                    String fileName = "insert.php";
                    URL url = new URL(emulatorIP+fileName);
                    HttpURLConnection http;
                    http = (HttpURLConnection) url.openConnection();
                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(tempName).append("/").append(tempTelNo).append();


                } catch (Exception e) {

                }
            }
        }.start();
    }
}