package com.example.ling.store.basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvBasketBinding;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {


    ItemRecvBasketBinding binding;
    Context context;

    public BasketAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding=ItemRecvBasketBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvBasketBinding binding;

        public ViewHolder(@NonNull ItemRecvBasketBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
