package com.example.ling.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvBoardUserBinding;

import java.util.ArrayList;


public class BoardMain_CommonAdapter extends RecyclerView.Adapter<BoardMain_CommonAdapter.ViewHolder> {
    ArrayList<BoardVO> list;
    BoardFragment fragment;

    public BoardMain_CommonAdapter(ArrayList<BoardVO> list, BoardFragment fragment) {
        this.list = list;
        this.fragment = fragment;
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
            fragment.changeFragment(2);
            Intent intent = new Intent(context, Board_DetailtActivity.class);
            intent.putExtra("board_no", list.get(i).getId());
            intent.putExtra("board_cd", list.get(i).getBoard_cd());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvBoardUserBinding binding;
        public ViewHolder(@NonNull ItemRecvBoardUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
