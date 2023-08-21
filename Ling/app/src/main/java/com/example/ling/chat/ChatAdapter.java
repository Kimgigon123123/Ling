package com.example.ling.chat;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        public ImageView imgv_chatImage;
        public TextView TextView_time;
        public View rootView;

        public LinearLayout ln_chat;



        public MyViewHolder(@NonNull View v) {
            super(v);
            TextView_nickname = v.findViewById(R.id.TextView_nickname);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            TextView_time = v.findViewById(R.id.tv_time);
            imgv_chatImage = v.findViewById(R.id.iv);
            ln_chat= v.findViewById(R.id.ln_chat);
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



        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_chat,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        ChatVO chat = mDataset.get(position);



        holder.TextView_nickname.setText(mDataset.get(position).getNickname());
        holder.TextView_msg.setText(mDataset.get(position).getMessage());
        holder.TextView_time.setText(mDataset.get(position).getTime());




        if (chat.getNickname() != null) {
            if (chat.getNickname().equals(mynickName)) {
                LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams) holder.imgv_chatImage.getLayoutParams();
                imageParams.gravity = Gravity.END;
                holder.imgv_chatImage.setLayoutParams(imageParams);

                LinearLayout.LayoutParams nicknameParams = (LinearLayout.LayoutParams) holder.TextView_nickname.getLayoutParams();
                nicknameParams.gravity = Gravity.END;
                holder.TextView_nickname.setLayoutParams(nicknameParams);

                LinearLayout.LayoutParams msgParams = (LinearLayout.LayoutParams) holder.TextView_msg.getLayoutParams();
                msgParams.gravity = Gravity.END;
                holder.TextView_msg.setLayoutParams(msgParams);

                LinearLayout.LayoutParams timeParams = (LinearLayout.LayoutParams) holder.TextView_time.getLayoutParams();
                timeParams.gravity = Gravity.END;
                holder.TextView_time.setLayoutParams(timeParams);
            } else {
                LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams) holder.imgv_chatImage.getLayoutParams();
                imageParams.gravity = Gravity.START;
                holder.imgv_chatImage.setLayoutParams(imageParams);

                LinearLayout.LayoutParams nicknameParams = (LinearLayout.LayoutParams) holder.TextView_nickname.getLayoutParams();
                nicknameParams.gravity = Gravity.START;
                holder.TextView_nickname.setLayoutParams(nicknameParams);

                LinearLayout.LayoutParams msgParams = (LinearLayout.LayoutParams) holder.TextView_msg.getLayoutParams();
                msgParams.gravity = Gravity.START;
                holder.TextView_msg.setLayoutParams(msgParams);

                LinearLayout.LayoutParams timeParams = (LinearLayout.LayoutParams) holder.TextView_time.getLayoutParams();
                timeParams.gravity = Gravity.START;
                holder.TextView_time.setLayoutParams(timeParams);
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
