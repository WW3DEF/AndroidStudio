package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ListView listView = (ListView)findViewById(R.id.listview);
        myAdapter myAdapter = new myAdapter();
        Intent intent = getIntent();
        int[] count = intent.getIntArrayExtra("count");
        int[] imgDrs = intent.getIntArrayExtra("imgDrs");
        String[] imgNames = intent.getStringArrayExtra("imgNames");
        listView.setAdapter(myAdapter);
        for(int i = 0; i < count.length; i++){
            myAdapter.addItems(count[i],imgDrs[i],imgNames[i]);
        }
        Button button = (Button) findViewById(R.id.btnGotoVote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}