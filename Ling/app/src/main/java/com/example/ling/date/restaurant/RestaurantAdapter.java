package com.example.ling.date.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvRestactBinding;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    ItemRecvRestactBinding binding;
    Context context;

    public RestaurantAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvRestactBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvRestaurant.setImageResource(R.drawable.ic_launcher_background);
        h.binding.tvRname.setText("이름");
        h.binding.tvRaddr.setText("주소");
        h.binding.lnRestaurant.setOnClickListener(v -> {
            Intent intent = new Intent(context, RestDetailActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecvRestactBinding binding;

        public ViewHolder(@NonNull ItemRecvRestactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
