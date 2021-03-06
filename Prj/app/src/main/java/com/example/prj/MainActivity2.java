package com.example.prj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {
    private DrawerLayout drawer;
    DrawerLayout drawerLayout;
    TextView id, nickname;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawerLayout = findViewById(R.id.drawer_layout);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

//        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        if(savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
        }

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        TextView tvName = findViewById(R.id.tvName);
        TextView tvId = findViewById(R.id.tvId);
        tvName.setText(sessionManager.getName());
        tvId.setText(sessionManager.getId());
    }

    void showDeleteDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(MainActivity2.this)
                .setTitle("????????????")
                .setMessage("????????? ?????????????????????????")
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView tvId = findViewById(R.id.tvId);
                        String tI = tvId.getText().toString().trim();
                        dataDelete(tI);
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity2.this, "??????????????????", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
    void showLogoutDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(MainActivity2.this)
                .setTitle("????????????")
                .setMessage("???????????? ???????????????????")
                .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity2.this, "????????????", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity2.this, "??????????????????", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        System.out.println("hello");
        activity.startActivity(intent);
    }
    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        redirectActivity(this, MainActivity2.class);
    }
    public void ClickProfile(View view){
        redirectActivity(this, ProfileFragment.class);
    }
    public void ClickProfileEdit(View view){
        redirectActivity(this, EditFragment.class);
    }
    public void ClickDelete(View view){
        showDeleteDialog();
    }
    public void ClickLogout(View view){
        showLogoutDialog();
    }
    public void ClickNoteAdd(View view) {
        redirectActivity(this, NoteAdd.class);
    }
    public void ClickSearch(View view) {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        TextView etMsearch = findViewById(R.id.etMSearch);
        String searchCon = etMsearch.getText().toString();
        sessionManager.setSearch(searchCon);
        redirectActivity(this, SearchResult.class);
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
                    Log.e("?????? ??????", "????????? ????????? ????????????" + str);
                    InputStreamReader isr = new InputStreamReader(http.getInputStream(), "utf8");
                    BufferedReader reader = new BufferedReader(isr);


                    StringBuilder builder = new StringBuilder();
                        String resultData = builder.toString();
                        final String[] sResult = resultData.split("/");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity2.this, "????????????", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        });
                } catch (Exception e) {
                    Log.e("?????? ??????", "????????? ????????? ?????? ??????");
                }
            }
        }.start();
    }
}