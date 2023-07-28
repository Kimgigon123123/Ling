package com.example.ling.store.myinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvStoreMyinfoBuylistBinding;
import com.example.ling.databinding.ItemRecvStoreMyinfoReturnBinding;
import com.example.ling.databinding.ItemRecvStoreMyinfoZzimBinding;

public class ReturnAdapter extends RecyclerView.Adapter<ReturnAdapter.ViewHolder>{

    ItemRecvStoreMyinfoReturnBinding binding;
    Context context;

    public ReturnAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding= ItemRecvStoreMyinfoReturnBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreMyinfoReturnBinding binding;


        public ViewHolder(@NonNull ItemRecvStoreMyinfoReturnBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }



}
