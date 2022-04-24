package com.example.tablayout002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                visiblePage(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    public void visiblePage(int position) {
        TextView txtTab1 = (TextView)findViewById(R.id.txtTab1);
        TextView txtTab2 = (TextView)findViewById(R.id.txtTab2);
        TextView txtTab3 = (TextView)findViewById(R.id.txtTab3);
        if(position == 0) {
            txtTab1.setVisibility(View.VISIBLE);
            txtTab2.setVisibility(View.INVISIBLE);
            txtTab3.setVisibility(View.INVISIBLE);
        } else if(position == 1) {
            txtTab1.setVisibility(View.INVISIBLE);
            txtTab2.setVisibility(View.VISIBLE);
            txtTab3.setVisibility(View.INVISIBLE);
        } else {
            txtTab1.setVisibility(View.INVISIBLE);
            txtTab2.setVisibility(View.INVISIBLE);
            txtTab3.setVisibility(View.VISIBLE);
        }
    }
}