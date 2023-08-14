package com.example.ling.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvBoardNoticeBinding;

import java.util.ArrayList;


public class Board_NoticeAdapter extends RecyclerView.Adapter<Board_NoticeAdapter.ViewHolder> {
    ArrayList<BoardVO> list;

    public Board_NoticeAdapter(ArrayList<BoardVO> list) {
        this.list = list;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvBoardNoticeBinding binding = ItemRecvBoardNoticeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.noticeRecvNum.setText(list.get(i).getId());
        h.binding.noticeRecvTitle.setText(list.get(i).getTitle());
        h.binding.noticeRecvDate.setText(list.get(i).getWritedate());
        h.binding.noticeRecvCnt.setText(list.get(i).getReadcnt()+"");

        h.binding.lnNotice.setOnClickListener(v->{
            Intent intent = new Intent(context, Notice_contextActivity.class);
            intent.putExtra("board_no", list.get(i).getId());
            intent.putExtra("board_cd", list.get(i).getBoard_cd());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvBoardNoticeBinding binding;
        public ViewHolder(@NonNull ItemRecvBoardNoticeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
