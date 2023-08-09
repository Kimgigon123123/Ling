package com.example.ling.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvBoardNoticeBinding;
import com.example.ling.databinding.ItemRecvBoardNoticeContextBinding;

import java.util.List;

public class Board_Notice_ContentAdapter extends RecyclerView.Adapter<Board_Notice_ContentAdapter.ViewHolder>{


    BoardVO boardvo;



    Context context;

    public Board_Notice_ContentAdapter(BoardVO boardvo) {
        this.boardvo = boardvo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvBoardNoticeContextBinding binding = ItemRecvBoardNoticeContextBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Board_Notice_ContentAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.noticeRecvNum.setText(boardvo.getId());
        h.binding.noticeRecvTitle.setText(boardvo.getTitle());
        h.binding.noticeRecvDate.setText(boardvo.getWritedate());
        h.binding.noticeRecvContent.setText(boardvo.getContent());
        h.binding.noticeRecvCnt.setText(boardvo.getReadcnt()+"");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvBoardNoticeContextBinding binding;
        public ViewHolder(@NonNull ItemRecvBoardNoticeContextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
