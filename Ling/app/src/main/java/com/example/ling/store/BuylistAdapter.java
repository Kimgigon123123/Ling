package com.example.ling.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvStoreMyinfoBuylistBinding;
import com.example.ling.databinding.ItemRecvStoreMyinfoZzimBinding;

public class BuylistAdapter extends RecyclerView.Adapter<BuylistAdapter.ViewHolder> {


    ItemRecvStoreMyinfoBuylistBinding binding;

    Context context;

    public BuylistAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BuylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvStoreMyinfoBuylistBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BuylistAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreMyinfoBuylistBinding binding;


        public ViewHolder(@NonNull ItemRecvStoreMyinfoBuylistBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}



