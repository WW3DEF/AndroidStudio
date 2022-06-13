package com.example.prj;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends BaseAdapter {
    private ArrayList<DataTransfer> transferArrayList = new ArrayList<DataTransfer>();
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
//        ImageView docClass = view.findViewById(R.id.listClass);
        TextView docTitle = view.findViewById(R.id.listTitle);
        TextView docSummary = view.findViewById(R.id.listSummary);
        TextView docDate = view.findViewById(R.id.listDate);

        DataTransfer dtos = transferArrayList.get(i);

        docNum.setText(dtos.getDocNum());
//        docClass.setImageDrawable(dtos.getDocClass());
        docTitle.setText(dtos.getDocTitle());
        docSummary.setText(dtos.getDocSummary());
        docDate.setText(dtos.getDocDate());

        return view;
    }
    public void addItem(String docNum, String docTitle, String docSummary, String docDate) {
            DataTransfer dto = new DataTransfer();
            dto.setDocNum(docNum);
//            dto.setDocClass(docClass);
            dto.setDocTitle(docTitle);
            dto.setDocSummary(docSummary);
            dto.setDocDate(docDate);
    }
}
