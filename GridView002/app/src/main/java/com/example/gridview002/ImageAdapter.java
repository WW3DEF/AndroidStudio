package com.example.gridview002;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    int[] imageId = {R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4,
    R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4,
    R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4,
    R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4,
    R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4};
    ImageAdapter(Context c){
        context = c;
    }
    @Override
    public int getCount() {
        return imageId.length;
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
        ImageView imageView;
        if(convertView == null) {
           imageView = new ImageView(context);
        }
        else {
            imageView = (ImageView)convertView;
        }
        imageView.setImageResource(imageId[position]);
        return imageView;
    }
}
