package com.example.ling.date.tour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvTouractBinding;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {

    ItemRecvTouractBinding binding;
    Context context;

    public TourAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvTouractBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvTour.setImageResource(R.drawable.ic_launcher_background);
        h.binding.tvTname.setText("이름");
        h.binding.tvTaddr.setText("주소");
        h.binding.lnTour.setOnClickListener(v -> {
            Intent intent = new Intent(context, TourDetailActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecvTouractBinding binding;

        public ViewHolder(@NonNull ItemRecvTouractBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
