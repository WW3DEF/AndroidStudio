package com.example.exam;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class myAdapter extends BaseAdapter {
    ArrayList<objectlist> objectlists = new ArrayList<objectlist>();

    @Override
    public int getCount() {
        return objectlists.size();
    }

    @Override
    public Object getItem(int i) {
        return objectlists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            Context context = viewGroup.getContext();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview, viewGroup, false);
        }
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textView1 = (TextView) view.findViewById(R.id.textView2);

        objectlist objectlist = objectlists.get(i);

        imageView.setImageResource(objectlist.getImgDrs());
        textView.setText(objectlist.getImgNames());
        textView1.setText(objectlist.getCount()+ "");
        return view;
    }
    public void addItems(int count, int imgDrs, String imgNames) {
        objectlist objectlist = new objectlist(count, imgDrs, imgNames);

        objectlists.add(objectlist);
    }
}
