package com.example.ling.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvStoreCoBinding;
import com.example.ling.databinding.ItemRecvStoreMainBinding;

public class StoreMainAdapter extends RecyclerView.Adapter<StoreMainAdapter.ViewHolder> {

    ItemRecvStoreMainBinding binding;

    Context context;

    public StoreMainAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvStoreMainBinding.inflate(inflater,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreMainBinding binding;

        public ViewHolder(@NonNull ItemRecvStoreMainBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
