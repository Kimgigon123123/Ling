package com.example.ling.chat;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonVar;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{

    private List<ChatVO> mDataset;
    Context context;

    private AlertDialog deleteDialog;
    private int selectedItemPosition = RecyclerView.NO_POSITION;
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


            TextView_msg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItemPosition = getAdapterPosition();
                    showDeleteDialog();
                }
            });
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_chat_delete, null);
        TextView textDeleteMessage = dialogView.findViewById(R.id.textDeleteMessage);
        Button btnConfirmDelete = dialogView.findViewById(R.id.btnConfirmDelete);
        Button btnCancelDelete = dialogView.findViewById(R.id.btnCancelDelete);

        builder.setView(dialogView);
        deleteDialog = builder.create();

        btnConfirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선택한 메시지 삭제 처리
                if (selectedItemPosition != RecyclerView.NO_POSITION) {
                    mDataset.remove(selectedItemPosition);
                    notifyItemRemoved(selectedItemPosition);
                    deleteDialog.dismiss();
                    selectedItemPosition = RecyclerView.NO_POSITION;
                }
            }
        });

        btnCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
                selectedItemPosition = RecyclerView.NO_POSITION;
            }
        });

        deleteDialog.show();
    }



    public ChatAdapter(List<ChatVO> mDataset, Context context, String myNickName) {
        this.mDataset = mDataset;
        this.mynickName = mynickName;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        context = parent.getContext();
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



        //로그인 한 사람의 닉네임에 따라 채팅 위치 조정
        if (chat.getNickname() != null) {
            if (chat.getNickname().equals(mynickName)) {


                holder.imgv_chatImage.setVisibility(View.GONE);

                LinearLayout.LayoutParams nicknameParams = (LinearLayout.LayoutParams) holder.TextView_nickname.getLayoutParams();
                nicknameParams.gravity = Gravity.END;
                holder.TextView_nickname.setLayoutParams(nicknameParams);

                LinearLayout.LayoutParams msgParams = (LinearLayout.LayoutParams) holder.TextView_msg.getLayoutParams();
                msgParams.gravity = Gravity.END;
                holder.TextView_msg.setLayoutParams(msgParams);

                LinearLayout.LayoutParams timeParams = (LinearLayout.LayoutParams) holder.TextView_time.getLayoutParams();
                timeParams.gravity = Gravity.END;
                holder.TextView_time.setLayoutParams(timeParams);

                holder.TextView_msg.setBackgroundResource(R.drawable.chat_message);
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

                holder.TextView_msg.setBackgroundResource(R.drawable.other_chat_message);
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
