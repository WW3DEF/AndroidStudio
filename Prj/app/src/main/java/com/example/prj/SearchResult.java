package com.example.prj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchResult extends AppCompatActivity {
    //    Handler handler = new Handler();
//    private Context context;
//    SessionManager sessionManager = new SessionManager(getApplicationContext());
//    Drawable drawSC = ContextCompat.getDrawable(context, R.drawable.ic_science);
//    Drawable drawMath = ContextCompat.getDrawable(context, R.drawable.ic_math);
//    Drawable drawPhilosophy = ContextCompat.getDrawable(context, R.drawable.ic_philosophy);
//    Drawable drawAstronomy = ContextCompat.getDrawable(context, R.drawable.ic_astronomy);
//    Drawable drawOthers = ContextCompat.getDrawable(context, R.drawable.ic_others);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ListView listView = findViewById(R.id.lvResult);
        EditText etSearch = findViewById(R.id.etSearch);
        DataAdapter adapter = new DataAdapter();
//        etSearch.setText(sessionManager.getSearch());
        listView.setAdapter(adapter);
//        dataSearch(sessionManager.getSearch());
        for (int i = 0; i < 4; i++) {
            adapter.addItem("ㅇㅇ", "ㅇㅇ", "ㅇㅇ", "ㅇㅇ");
        }
    }
//    public void dataSearch(String search){
//        new Thread(){
//            public void run(){
//                try{
//                    URL url = new URL("http://10.0.2.2/docsearch.php");
//                    HttpURLConnection http;
//                    http = (HttpURLConnection)url.openConnection();
//
//                    http.setDefaultUseCaches(false);
//                    http.setDoInput(true);
//                    http.setRequestMethod("POST");
//                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//
//                    StringBuffer buffer = new StringBuffer();
//                    buffer.append("name").append("=").append(search);
//
//                    String str;
//                    str = buffer.toString();
//                    OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf8");
//                    osw.write(str);
//                    osw.flush();
//                    Log.e("검색 모듈", "서버로 데이터 전달완료" + str);
//                    InputStreamReader isr = new InputStreamReader(http.getInputStream(), "utf8");
//                    BufferedReader reader = new BufferedReader(isr);
//
//                    String str2;
//                    StringBuilder builder = new StringBuilder();
//                    while((str2 = reader.readLine()) != null){
//                        builder.append(str2);
//                        String resultData = builder.toString();
//                        final String[] sResult = resultData.split("/");
//                        Log.e("검색 모듈", "서버에서 온 데이터" + str2);
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
////                                for(int i = 0; i < sResult.length; i++) {
////                                    DataAdapter dataAdapter = new DataAdapter();
////                                    if(sResult[2] == "과학") {
////                                        dataAdapter.addItem(sResult[0], sResult[1], sResult[2], sResult[3], sResult[4]);
////                                    } else if(sResult[2] == "철학") {
////                                        dataAdapter.addItem(sResult[0], sResult[0], sResult[2], sResult[3], sResult[4]);
////                                    } else if(sResult[2] == "수학") {
////                                        dataAdapter.addItem(sResult[0], sResult[0], sResult[2], sResult[3], sResult[4]);
////                                    } else if(sResult[2] == "천문학") {
////                                        dataAdapter.addItem(sResult[0], drawAstronomy, sResult[2], sResult[3], sResult[4]);
////                                    } else if(sResult[2] == "기타") {
////                                        dataAdapter.addItem(sResult[0], drawOthers, sResult[2], sResult[3], sResult[4]);
////                                    }
////                                }
//                            }
//                        });
//                    }
//                } catch (Exception e){
//                    Log.e("검색 모듈", "서버로 데이터 전달 실패");
//                }
//            }
//        }.start();
//}
    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        System.out.println("hello");
        activity.startActivity(intent);
    }
    public void ClickBackHome(View view) { redirectActivity(this, MainActivity2.class);}
}