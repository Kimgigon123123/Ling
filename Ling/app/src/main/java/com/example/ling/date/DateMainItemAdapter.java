package com.example.ling.date;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvDateBinding;
import com.example.ling.date.list.DateDetailActivity;
import com.example.ling.date.list.DateInfoVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DateMainItemAdapter extends RecyclerView.Adapter<DateMainItemAdapter.ViewHolder> {


    ArrayList<DateInfoVO> list;
    Context context;

    public DateMainItemAdapter(ArrayList<DateInfoVO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRecvDateBinding binding = ItemRecvDateBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        String imageUrl=list.get(i).getDate_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvTour);
        h.binding.cvTour.setOnClickListener(v -> {
            Intent intent = new Intent(context, DateDetailActivity.class);
            intent.putExtra("vo",list.get(i));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvDateBinding binding;

        public ViewHolder(@NonNull ItemRecvDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
