package com.example.ling.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.Board_ReCommentActivity;
import com.example.ling.databinding.ItemRecvCommentBinding;

import java.util.ArrayList;

public class Board_CommentAdapter extends RecyclerView.Adapter<Board_CommentAdapter.ViewHolder>{
    ArrayList<BoardCommentVO> list;
    Context context;
    public Board_CommentAdapter(ArrayList<BoardCommentVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvCommentBinding binding = ItemRecvCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.tvCommentId.setText(list.get(i).getWriter());
        h.binding.tvCommentWritedate.setText(list.get(i).getWritedate());
        h.binding.tvCommentContent.setText(list.get(i).getContent());
        h.binding.btnCommentComment.setOnClickListener(v->{
            Intent intent = new Intent(context, Board_ReCommentActivity.class);
            intent.putExtra("id", list.get(i).getId());
            intent.putExtra("tvCommentId", list.get(i).getWriter());
            intent.putExtra("tvCommentWritedate", list.get(i).getWritedate());
            intent.putExtra("tvCommentContent", list.get(i).getContent());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvCommentBinding binding;
        public ViewHolder(@NonNull ItemRecvCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
