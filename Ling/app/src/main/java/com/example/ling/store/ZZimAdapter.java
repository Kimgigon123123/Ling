package com.example.ling.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvStoreCoBinding;
import com.example.ling.databinding.ItemRecvStoreMyinfoZzimBinding;

public class ZZimAdapter extends RecyclerView.Adapter<ZZimAdapter.ViewHolder> {

    ItemRecvStoreMyinfoZzimBinding binding;

    Context context;

    public ZZimAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvStoreMyinfoZzimBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreMyinfoZzimBinding binding;


        public ViewHolder(@NonNull ItemRecvStoreMyinfoZzimBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
