package com.example.ling.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ling.R;

import java.util.ArrayList;

public class Chat1Adapter extends BaseAdapter {
    ArrayList<ChatDTO> list;
    LayoutInflater layoutInflater;

    public Chat1Adapter(ArrayList<ChatDTO> list, LayoutInflater layoutInflater) {
        this.list = list;
        this.layoutInflater = layoutInflater;
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
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        ChatDTO dto = list.get(i);
        View itemView = null;

        //메세지가 내 메세지인지??
        if(dto.getName().equals(G.nickName)){
            itemView= layoutInflater.inflate(R.layout.my_msgbox,parent,false);
        }else{
            itemView= layoutInflater.inflate(R.layout.other_msgbox,parent,false);
        }

        //만들어진 itemView에 값들 설정
//        CircleImageView iv= itemView.findViewById(R.id.iv);
        TextView tvName= itemView.findViewById(R.id.tv_name);
        TextView tvMsg= itemView.findViewById(R.id.tv_msg);
        TextView tvTime= itemView.findViewById(R.id.tv_time);

        tvName.setText(dto.getName());
        tvMsg.setText(dto.getMessage());
        tvTime.setText(dto.getTime());

//        Glide.with(itemView).load(item.getPofileUrl()).into(iv);

        return itemView;



    }
}
