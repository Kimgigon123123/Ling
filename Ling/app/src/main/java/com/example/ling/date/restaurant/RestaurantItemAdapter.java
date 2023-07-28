package com.example.ling.date.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvRestaurantBinding;

public class RestaurantItemAdapter extends RecyclerView.Adapter<RestaurantItemAdapter.ViewHolder> {

    ItemRecvRestaurantBinding binding;
    Context context;

    public RestaurantItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvRestaurantBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvRestaurant.setImageResource(R.drawable.ic_launcher_background);
        h.binding.tvRestaurant.setText("맛집");
        h.binding.cvRestaurant.setOnClickListener(v -> {
            Intent intent = new Intent(context, RestaurantActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvRestaurantBinding binding;

        public ViewHolder(@NonNull ItemRecvRestaurantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}