package com.example.gridview002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView gvPoster;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvPoster = (GridView)findViewById(R.id.gvPoster);
        gvPoster.setAdapter(new ImageAdapter(this));
    }
}