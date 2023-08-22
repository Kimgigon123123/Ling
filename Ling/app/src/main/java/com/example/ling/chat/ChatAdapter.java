package com.example.ling.chat;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonVar;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{

    private List<ChatVO> mDataset;

    private String mynickName = CommonVar.loginInfo.getName();

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_nickname;
        public TextView TextView_msg;

        public TextView TextView_time;
        public View rootView;



        public MyViewHolder(@NonNull View v) {
            super(v);
            TextView_nickname = v.findViewById(R.id.TextView_nickname);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            TextView_time = v.findViewById(R.id.tv_time);
            rootView = v;
        }
    }



    public ChatAdapter(List<ChatVO> mDataset, Context context, String myNickName) {
        this.mDataset = mDataset;
        this.mynickName = mynickName;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_chat,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        ChatVO chat = mDataset.get(position);

        holder.TextView_nickname.setText(mDataset.get(position).getNickname());
        holder.TextView_msg.setText(mDataset.get(position).getMessage());
        holder.TextView_time.setText(mDataset.get(position).getTime());




        if(chat.getNickname() != null) {
            if(chat.getNickname().equals(mynickName)) {
                holder.TextView_nickname.setGravity(Gravity.END);
                holder.TextView_msg.setGravity(Gravity.END);
                holder.TextView_time.setGravity(Gravity.END);

            }else{
                holder.TextView_msg.setGravity(Gravity.START);
                holder.TextView_nickname.setGravity(Gravity.START);
                holder.TextView_time.setGravity(Gravity.START);
            }
        }


    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }


    public ChatVO getChat(int position){
        return mDataset != null ? mDataset.get(position) : null;
    }
    public void addChat(ChatVO chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //갱신

    }
}
