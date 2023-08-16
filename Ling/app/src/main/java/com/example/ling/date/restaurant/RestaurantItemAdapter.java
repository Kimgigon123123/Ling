package com.example.ling.date.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvRestaurantBinding;
import com.example.ling.date.DateInfoVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantItemAdapter extends RecyclerView.Adapter<RestaurantItemAdapter.ViewHolder> {

    ItemRecvRestaurantBinding binding;
    ArrayList<DateInfoVO> list;
    Context context;

    public RestaurantItemAdapter(Context context, ArrayList<DateInfoVO> list) {
        this.context = context;
        this.list = list;
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
        String imageUrl=list.get(i).getDate_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvRestaurant);
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
