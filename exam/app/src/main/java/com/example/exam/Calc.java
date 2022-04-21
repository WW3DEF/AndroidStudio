package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calc extends AppCompatActivity {
    String num1, num2;
    int result1;
    double result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName2);

        Button button = (Button) findViewById(R.id.btnPlus);
        Button button1 = (Button) findViewById(R.id.btnMinus);
        Button button2 = (Button) findViewById(R.id.btnMultiply);
        Button button3 = (Button) findViewById(R.id.btnDivide);

        TextView textView = (TextView)findViewById(R.id.textView3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editText.getText().toString();
                num2 = editText1.getText().toString();
                result1 = Integer.parseInt(num1) + Integer.parseInt(num2);
                textView.setText("계산결과 "+result1);
                Toast.makeText(Calc.this, ""+result1, Toast.LENGTH_SHORT).show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editText.getText().toString();
                num2 = editText1.getText().toString();
                result1 = Integer.parseInt(num1) - Integer.parseInt(num2);
                textView.setText("계산결과 "+result1);
                Toast.makeText(Calc.this, ""+result1, Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editText.getText().toString();
                num2 = editText1.getText().toString();
                result1 = Integer.parseInt(num1) * Integer.parseInt(num2);
                textView.setText("계산결과 "+result1);
                Toast.makeText(Calc.this, ""+result1, Toast.LENGTH_SHORT).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editText.getText().toString();
                num2 = editText1.getText().toString();
                result2 = (double) Double.parseDouble(num1) / Double.parseDouble(num2);
                textView.setText("계산결과 "+result2);
                Toast.makeText(Calc.this, ""+result2, Toast.LENGTH_SHORT).show();
            }
        });
    }
}