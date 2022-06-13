package com.example.prj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowDocument extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_document);
        TextView viewNum = findViewById(R.id.docViewNum);
        TextView viewTitle = findViewById(R.id.docViewTitle);
        TextView viewClass = findViewById(R.id.docViewClass);
        TextView viewSummary = findViewById(R.id.docViewSummary);
        TextView viewExplain = findViewById(R.id.docViewExplain);
        TextView viewDate = findViewById(R.id.docViewDate);

        Intent intent = getIntent();
        String docNum = intent.getStringExtra("docNum");
        String docTitle = intent.getStringExtra("docTitle");
        String docClass = intent.getStringExtra("docClass");
        String docSummary = intent.getStringExtra("docSummary");
        String docExplain = intent.getStringExtra("docExplain");
        String docDate = intent.getStringExtra("docDate");

        viewNum.setText(docNum);
        viewTitle.setText(docTitle);
        viewClass.setText(docClass);
        viewSummary.setText(docSummary);
        viewExplain.setText(docExplain);
        viewDate.setText(docDate);
    }
    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        System.out.println("hello");
        activity.startActivity(intent);
    }
    public void ClickBackSearchResult(View view) { redirectActivity(this, SearchResult.class);}
}