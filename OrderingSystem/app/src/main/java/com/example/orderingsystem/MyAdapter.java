package com.example.orderingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.orderingsystem.R;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private String[] strings;
    public static int mPosition;

    public MyAdapter(Context context, String[] strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listitemright, null);
  /*      TextView tv=(TextView)convertView.findViewById(R.id.categorylistleft);
        mPosition=position;
        tv.setText(strings[position]);
        if (position==DishListActivity.mPosition){
            convertView.setBackgroundResource(Color.parseColor("#f4f4f4"));
        }else {
            convertView.setBackgroundResource(Color.parseColor("#f4f4f4"));
        }*/
        return convertView;
    }
}


