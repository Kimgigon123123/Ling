package com.example.ling.testchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvChatBinding;

public class TestChatAdapter extends RecyclerView.Adapter<TestChatAdapter.ViewHolder> {

    ItemRecvChatBinding binding;

    Context context;

    public TestChatAdapter(Context context) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvChatBinding binding;

        public ViewHolder(@NonNull ItemRecvChatBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
