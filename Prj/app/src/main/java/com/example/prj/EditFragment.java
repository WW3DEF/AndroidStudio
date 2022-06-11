package com.example.prj;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class EditFragment extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);

        drawerLayout = findViewById(R.id.drawer_layout);


        SessionManager sessionManager = new SessionManager(getApplicationContext());
        TextView tvName = findViewById(R.id.tvName);
        TextView tvId = findViewById(R.id.tvId);

        tvName.setText(sessionManager.getName());
        tvId.setText(sessionManager.getId());
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
    }
    public void ClickLogout(View view){
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity2.closeDrawer(drawerLayout);
    }
}
