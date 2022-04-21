package com.example.gallery002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Gallery galFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] imgIDs = {R.drawable.canada, R.drawable.france, R.drawable.korea, R.drawable.mexico, R.drawable.poland, R.drawable.saudi_arabia,
                R.drawable.canada, R.drawable.france, R.drawable.korea, R.drawable.mexico, R.drawable.poland, R.drawable.saudi_arabia,
                R.drawable.canada, R.drawable.france, R.drawable.korea, R.drawable.mexico, R.drawable.poland, R.drawable.saudi_arabia,
                R.drawable.canada, R.drawable.france, R.drawable.korea, R.drawable.mexico, R.drawable.poland, R.drawable.saudi_arabia,
                R.drawable.canada, R.drawable.france, R.drawable.korea, R.drawable.mexico, R.drawable.poland, R.drawable.saudi_arabia};
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), R.layout.row , imgIDs);
        galFlag = (Gallery) findViewById(R.id.galFlag);
        galFlag.setAdapter(myAdapter);
        final ImageView imageView = (ImageView)findViewById(R.id.imgMain);
        galFlag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                imageView.setImageResource(imgIDs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}