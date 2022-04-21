package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] imgIds = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9};
    int[] count = new int[9];
    String[] imgNames = {"그림1","그림2","그림3","그림4","그림5","그림6","그림7","그림8","그림9"};
    int[] imgDrs = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < 9; i++){
            int idx = i;
            ImageView imageView = (ImageView) findViewById(imgIds[i]);
            imageView.setImageResource(imgDrs[i]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count[idx]++;
                    Toast.makeText(MainActivity.this, "" + count[idx], Toast.LENGTH_SHORT).show();
                }
            });
        }
        Button button = (Button) findViewById(R.id.btnGotoResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), result.class);
                intent.putExtra("imgNames", imgNames);
                intent.putExtra("imgDrs", imgDrs);
                intent.putExtra("count", count);
                startActivity(intent);
            }
        });
        Button button1 = (Button) findViewById(R.id.btnGotoCalc);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Calc.class);
                startActivity(intent);
            }
        });
    }
}