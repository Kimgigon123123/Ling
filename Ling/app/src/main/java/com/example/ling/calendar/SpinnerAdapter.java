package com.example.ling.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ling.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Spinner> list;

    public SpinnerAdapter(Context context, ArrayList<Spinner> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_anniversary, parent, false);
        TextView textName = rootView.findViewById(R.id.tv_spinner);
        ImageView image = rootView.findViewById(R.id.imgv_spinner);

        textName.setText(list.get(i).getName());
        image.setImageResource(list.get(i).getSpinnerImg());

        return rootView;
    }
}
