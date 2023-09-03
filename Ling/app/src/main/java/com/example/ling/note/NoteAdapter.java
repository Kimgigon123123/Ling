package com.example.ling.note;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvChatBinding;
import com.example.ling.databinding.ItemRecvNoteBinding;
import com.example.ling.photo.PhotoVO;
import com.example.ling.testchat.TestChatAdapter;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {


    ItemRecvNoteBinding binding;
    Context context;
    List<NoteVO> list;

    public NoteAdapter(List<NoteVO> list,Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvNoteBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.tvContent.setText(list.get(i).getContent());
        h.binding.tvName.setText(list.get(i).getName());
        h.binding.tvDate.setText(list.get(i).getWrite_date());

        h.binding.noteColor.setOnClickListener(v->{
            Intent intent = new Intent(context, NoteModifyActivity.class);
            intent.putExtra("note_id",list.get(i).getNote_id());
            intent.putExtra("content",list.get(i).getContent());
            intent.putExtra("view_range",list.get(i).getView_range());
            context.startActivity(intent);
        });

        if(CommonVar.loginInfo.getId().equals(list.get(i).getId())){
            h.binding.noteColor.setCardBackgroundColor(Color.YELLOW);
        }else{


        }

        if(list.get(i).view_range.equals("C")){
            h.binding.tvHeart.setVisibility(View.VISIBLE);
        }else{
            h.binding.tvPrivate.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvNoteBinding binding;

        public ViewHolder(@NonNull ItemRecvNoteBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
