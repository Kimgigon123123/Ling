package com.example.ling.store.myinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvStoreMyinfoBuylistBinding;
import com.example.ling.databinding.ItemRecvStoreMyinfoZzimBinding;
import com.example.ling.store.StorePurchaseActivity;

public class BuylistMoreAdapter extends RecyclerView.Adapter<BuylistMoreAdapter.ViewHolder>{

    ItemRecvStoreMyinfoBuylistBinding binding;
    Context context;

    public BuylistMoreAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding= ItemRecvStoreMyinfoBuylistBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {



        h.binding.imgvItem.setOnClickListener(v -> {
            Intent intent = new Intent(context, StorePurchaseActivity.class);
            context.startActivity(intent);
        });

//        h.binding.tvDeliveryCheck.setOnClickListener(v -> {
//            Intent intent = new Intent(context, DeliveryActivity.class);
//            context.startActivity(intent);
//        });
//
//        h.binding.tvReturn.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ReturnActivity.class);
//            context.startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreMyinfoBuylistBinding binding;


        public ViewHolder(@NonNull ItemRecvStoreMyinfoBuylistBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }



}
