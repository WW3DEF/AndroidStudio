package com.example.gallery002;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    int[] img;
    LayoutInflater inflater;
    MyAdapter(Context c, int layout, int[] img) {
        this.context = c;
        this.layout = layout;
        this.img = img;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(layout, null);
        }
            ImageView imageView = (ImageView)convertView.findViewById(R.id.imgRow);
            imageView.setImageResource(img[position]);
            return convertView;
    }
}
