package com.example.ling.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvBoardNoticeBinding;

import java.net.InetSocketAddress;
import java.util.ArrayList;




public class BoardMain_NoticeAdapter extends RecyclerView.Adapter<BoardMain_NoticeAdapter.ViewHolder> {
    ArrayList<BoardVO> list;
    BoardFragment fragment;

    public BoardMain_NoticeAdapter(ArrayList<BoardVO> list, BoardFragment fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    public BoardMain_NoticeAdapter(ArrayList<BoardVO> list) {
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
            fragment.changeFragment(1);
            Intent intent = new Intent(context, Notice_contextActivity.class);
            intent.putExtra("board_no", list.get(i).getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvBoardNoticeBinding binding;
        public ViewHolder(@NonNull ItemRecvBoardNoticeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
