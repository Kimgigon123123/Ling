package com.example.ling.date.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ActivityFestDetailBinding;
import com.example.ling.databinding.ItemRecvFestactBinding;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.ViewHolder> {
    ItemRecvFestactBinding binding;
    Context context;

    public FestivalAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvFestactBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvFestival.setImageResource(R.drawable.ic_launcher_background);
        h.binding.imgvFav2.setVisibility(View.INVISIBLE);
        h.binding.tvFname.setText("이름");
        h.binding.tvFaddr.setText("주소");
        h.binding.imgvFav.setOnClickListener(view -> {
            h.binding.imgvFav2.setVisibility(View.VISIBLE);
            h.binding.imgvFav.setVisibility(View.INVISIBLE);
        });
        h.binding.imgvFav2.setOnClickListener(v -> {
            h.binding.imgvFav2.setVisibility(View.INVISIBLE);
            h.binding.imgvFav.setVisibility(View.VISIBLE);
        });
        h.binding.lnFestival.setOnClickListener(v -> {
            Intent intent = new Intent(context, FestDetailActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecvFestactBinding binding;

        public ViewHolder(@NonNull ItemRecvFestactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
