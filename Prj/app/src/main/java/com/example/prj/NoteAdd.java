package com.example.prj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteAdd extends AppCompatActivity {
    String items[] = {"과학", "철학", "수학", "천문학", "기타"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteadd);
        Button btnUpload = findViewById(R.id.btnUpload);
        Spinner spinner = findViewById(R.id.noteSpinner);
        EditText etTitle = findViewById(R.id.docTitle);
        EditText etSummary = findViewById(R.id.docSummary);
        EditText etExplain = findViewById(R.id.docExplain);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                Toast.makeText(NoteAdd.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tt = etTitle.getText().toString().trim();
                String sp = spinner.getSelectedItem().toString().trim();
                String sm = etSummary.getText().toString().trim();
                String ex = etExplain.getText().toString().trim();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String time = simpleDateFormat.format(date);
                System.out.println(time);
                String sql = tt + "/" + sp + "/" + sm + "/" + ex + "/" + time;
                System.out.println(sql);
                dataInsert(sql);
                Toast.makeText(NoteAdd.this, "작성완료", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            }
        });
    }
    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        System.out.println("hello");
        activity.startActivity(intent);
    }
    public void ClickBackHome(View view) { redirectActivity(this, MainActivity2.class);}
    public void dataInsert(String sql){
        new Thread(){
            public void run(){
                try{
                    URL setURL = new URL("Http://10.0.2.2/docinsert.php/");
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