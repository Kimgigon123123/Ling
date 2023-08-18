package com.example.ling.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ling.databinding.ItemRecvRecommentBinding;

import java.util.ArrayList;

public class Board_ReCommentAdapter extends RecyclerView.Adapter<Board_ReCommentAdapter.ViewHolder>{
    ArrayList<BoardReCommentVO> list;
    Context context;
    public Board_ReCommentAdapter(ArrayList<BoardReCommentVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvRecommentBinding binding = ItemRecvRecommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.tvRecommentId.setText(list.get(i).getWriter());
        h.binding.tvRecommentWritedate.setText(list.get(i).getWritedate());
        h.binding.tvRecommentContent.setText(list.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvRecommentBinding binding;
        public ViewHolder(@NonNull ItemRecvRecommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
