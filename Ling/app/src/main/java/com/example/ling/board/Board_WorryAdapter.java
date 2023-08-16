package com.example.ling.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvBoardUserBinding;

import java.util.ArrayList;


public class Board_WorryAdapter extends RecyclerView.Adapter<Board_WorryAdapter.ViewHolder> {
    ArrayList<BoardVO> list;

    public Board_WorryAdapter(ArrayList<BoardVO> list) {
        this.list = list;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvBoardUserBinding binding = ItemRecvBoardUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.boardNum.setText(list.get(i).getId());
        h.binding.boardTitle.setText(list.get(i).getTitle());
        h.binding.boardWriter.setText(list.get(i).getWriter());
        h.binding.boardDate.setText(list.get(i).getWritedate());
        h.binding.boardCnt.setText(list.get(i).getReadcnt()+"");

        h.binding.lnUser.setOnClickListener(v->{
            Intent intent = new Intent(context, Notice_contextActivity.class);
            intent.putExtra("board_no", list.get(i).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvBoardUserBinding binding;
        public ViewHolder(@NonNull ItemRecvBoardUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}