package com.example.prj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class EditFragment extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);

        drawerLayout = findViewById(R.id.drawer_layout);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        TextView tvName = findViewById(R.id.tvName);
        TextView tvId = findViewById(R.id.tvId);
        TextView pfId = findViewById(R.id.profileEditId);
        TextView pfName = findViewById(R.id.profileEditName);
        TextView pfPwd= findViewById(R.id.profileEditPwd);
        tvName.setText(sessionManager.getName());
        tvId.setText(sessionManager.getId());
        pfId.setText(sessionManager.getId());
        pfName.setText(sessionManager.getName());
        pfPwd.setText(sessionManager.getPwd());

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = pfId.getText().toString().trim();
                String name = pfName.getText().toString().trim();
                String pwd = pfPwd.getText().toString().trim();
                String pastId = tvId.getText().toString().trim();
                String sql = id + "/" + name + "/" + pwd + "/" + pastId;
                dataUpdate(sql);
            }
        });
    }

    public void ClickMenu(View view){
        MainActivity2.openDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        MainActivity2.redirectActivity(this, MainActivity2.class);
    }
    public void ClickProfile(View view){
        MainActivity2.redirectActivity(this, ProfileFragment.class);
    }
    public void ClickProfileEdit(View view){
        MainActivity2.redirectActivity(this, EditFragment.class);
    }
    public void ClickDelete(View view){
            showDeleteDialog();
    }
    public void ClickLogout(View view){
            showLogoutDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity2.closeDrawer(drawerLayout);
    }
    void showDeleteDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(EditFragment.this)
                .setTitle("회원탈퇴")
                .setMessage("정말로 탈퇴하시겠습니까?")
                .setPositiveButton("탈퇴", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView tvId = findViewById(R.id.tvId);
                        String tI = tvId.getText().toString().trim();
                        dataDelete(tI);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditFragment.this, "취소됐습니다", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
    void showLogoutDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(EditFragment.this)
                .setTitle("로그아웃")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(EditFragment.this, "로그아웃", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditFragment.this, "취소됐습니다", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
    public void dataDelete(String id) {
        new Thread() {
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/userdelete.php");
                    HttpURLConnection http;
                    http = (HttpURLConnection) url.openConnection();

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


                    StringBuilder builder = new StringBuilder();
                    String resultData = builder.toString();
                    final String[] sResult = resultData.split("/");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EditFragment.this, "탈퇴완료", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    });
                } catch (Exception e) {
                    Log.e("검색 모듈", "서버로 데이터 전달 실패");
                }
            }
        }.start();
    }
    public void dataUpdate(String sql) {
        new Thread() {
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/userupdate.php");
                    HttpURLConnection http;
                    http = (HttpURLConnection) url.openConnection();

                    http.setDefaultUseCaches(false);
                    http.setDoInput(true);
                    http.setRequestMethod("POST");
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("name").append("=").append(sql);

                    String str;
                    str = buffer.toString();
                    OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf8");
                    osw.write(str);
                    osw.flush();
                    Log.e("검색 모듈", "서버로 데이터 전달완료" + str);
                    InputStreamReader isr = new InputStreamReader(http.getInputStream(), "utf8");
                    BufferedReader reader = new BufferedReader(isr);
                    StringBuilder builder = new StringBuilder();
                    String str2;
                    while((str2 = reader.readLine()) != null) {
                        builder.append(str2);
                        builder.append("\n");
                    }
                    String resultData = builder.toString();
                    final String[] sResult = resultData.split("/");
                    Log.e("검색 모듈", "서버에서 온 데이터" + str2);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView pfId = findViewById(R.id.profileEditId);
                            TextView pfName = findViewById(R.id.profileEditName);
                            TextView pfPwd= findViewById(R.id.profileEditPwd);
                            String id = pfId.getText().toString().trim();
                            String name = pfName.getText().toString().trim();
                            String pwd = pfPwd.getText().toString().trim();
                            SessionManager sessionManager = new SessionManager(getApplicationContext());
                            sessionManager.setId(id);
                            sessionManager.setName(name);
                            sessionManager.setPwd(pwd);
                            Toast.makeText(EditFragment.this, "수정완료", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                        }
                    });
                } catch (Exception e) {
                    Log.e("검색 모듈", "서버로 데이터 전달 실패");
                }
            }
        }.start();
    }
}
