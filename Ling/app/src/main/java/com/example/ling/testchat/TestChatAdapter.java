package com.example.ling.testchat;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvChatBinding;

import java.util.List;

public class TestChatAdapter extends RecyclerView.Adapter<TestChatAdapter.ViewHolder> {

    ItemRecvChatBinding binding;

    List<ChatVO> list;

    Context context;

    public TestChatAdapter(List<ChatVO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvChatBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

        h.binding.tvTime.setText(list.get(i).chat_time);
        h.binding.tvContent.setText(list.get(i).chat_content);
        if(CommonVar.loginInfo.getId().equals(list.get(i).getId())){
            h.binding.tvName.setText(list.get(i).getMyname());
            h.binding.chatColor.setCardBackgroundColor(Color.YELLOW);
        }else{
            h.binding.tvName.setText(list.get(i).getCouple_name());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvChatBinding binding;

        public ViewHolder(@NonNull ItemRecvChatBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
