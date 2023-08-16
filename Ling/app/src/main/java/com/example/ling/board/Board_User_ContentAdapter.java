package com.example.ling.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvBoardNoticeContextBinding;
import com.example.ling.databinding.ItemRecvBoardUserContextBinding;

public class Board_User_ContentAdapter extends RecyclerView.Adapter<Board_User_ContentAdapter.ViewHolder>{


    BoardVO boardvo;



    Context context;

    public Board_User_ContentAdapter(BoardVO boardvo) {
        this.boardvo = boardvo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvBoardUserContextBinding binding = ItemRecvBoardUserContextBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Board_User_ContentAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.userRecvNum.setText(boardvo.getId());
        h.binding.userRecvWriter.setText(boardvo.getWriter());
        h.binding.userRecvTitle.setText(boardvo.getTitle());
        h.binding.userRecvDate.setText(boardvo.getWritedate());
        h.binding.userRecvContent.setText(boardvo.getContent());
        h.binding.userRecvCnt.setText(boardvo.getReadcnt()+"");


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvBoardUserContextBinding binding;
        public ViewHolder(@NonNull ItemRecvBoardUserContextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
