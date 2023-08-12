package com.example.ling.date.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvFestivalBinding;

public class FestivalItemAdapter extends RecyclerView.Adapter<FestivalItemAdapter.ViewHolder> {

    ItemRecvFestivalBinding binding;
    int[] festImg;
    Context context;

    public FestivalItemAdapter(Context context, int[] festImg) {
        this.context = context;
        this.festImg = festImg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvFestivalBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvFestival.setImageResource(festImg[i]);
        h.binding.cvFestival.setOnClickListener(v -> {
            Intent intent = new Intent(context, FestivalActivity.class);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvFestivalBinding binding;

        public ViewHolder(@NonNull ItemRecvFestivalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
