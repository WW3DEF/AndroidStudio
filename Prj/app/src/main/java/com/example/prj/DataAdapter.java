package com.example.prj;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends BaseAdapter {
    private ArrayList<DataTransfer> transferArrayList = new ArrayList<>();
    @Override
    public int getCount() {
        return transferArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return transferArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            Context context = viewGroup.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dataresult, viewGroup, false);
        }
        TextView docNum = view.findViewById(R.id.listNum);
        ImageView docClass = view.findViewById(R.id.listClass);
        TextView docTitle = view.findViewById(R.id.listTitle);
        TextView docSummary = view.findViewById(R.id.listSummary);
        TextView docDate = view.findViewById(R.id.listDate);

        DataTransfer dtos = transferArrayList.get(i);

        docNum.setText(dtos.getDocNum());
        docTitle.setText(dtos.getDocTitle());
        docSummary.setText(dtos.getDocSummary());
        docDate.setText(dtos.getDocDate());

        switch (dtos.getDocClass()){
            case "과학":
                docClass.setImageResource(R.drawable.ic_science);
                break;
            case "수학":
                docClass.setImageResource(R.drawable.ic_math);
                break;
            case "철학":
                docClass.setImageResource(R.drawable.ic_philosophy);
                break;
            case "천문학":
                docClass.setImageResource(R.drawable.ic_astronomy);
                break;
            case "기타":
                docClass.setImageResource(R.drawable.ic_others);
                break;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), ShowDocument.class);
                intent.putExtra("docNum",dtos.getDocNum());
                intent.putExtra("docTitle",dtos.getDocTitle());
                intent.putExtra("docClass",dtos.getDocClass());
                intent.putExtra("docSummary",dtos.getDocSummary());
                intent.putExtra("docExplain",dtos.getDocExplain());
                intent.putExtra("docDate",dtos.getDocDate());
                viewGroup.getContext().startActivity(intent);
            }
        });
        return view;
    }
    public void addItem(DataTransfer dto) {
        transferArrayList.add(dto);
    }
}
