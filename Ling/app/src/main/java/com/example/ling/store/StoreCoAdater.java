package com.example.ling.store;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvDibsBinding;
import com.example.ling.databinding.ItemRecvStoreCoBinding;
import com.example.ling.store.storeCO.StoreCOVO;

import java.util.ArrayList;


public class StoreCoAdater extends RecyclerView.Adapter<StoreCoAdater.ViewHolder> {

    ItemRecvStoreCoBinding binding;
    Context context;

    ArrayList<StoreCOVO> list;

    public StoreCoAdater(Context context) {
        this.context = context;
    }

    public StoreCoAdater(ArrayList<StoreCOVO> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvStoreCoBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

        h.binding.imgvItem.setImageResource(R.drawable.ic_launcher_background);
        h.binding.tvName.setText(list.get(i).getItem_name());
        h.binding.tvPrice.setText(list.get(i).getItem_price()+"원");

        h.binding.intoItem.setOnClickListener(v -> {
            Intent intent = new Intent(context,StorePurchaseActivity.class);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreCoBinding binding;

        public ViewHolder(@NonNull ItemRecvStoreCoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}
