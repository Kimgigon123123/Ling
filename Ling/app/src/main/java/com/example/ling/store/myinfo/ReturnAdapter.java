package com.example.ling.store.myinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvStoreMyinfoBuylistBinding;
import com.example.ling.databinding.ItemRecvStoreMyinfoReturnBinding;
import com.example.ling.databinding.ItemRecvStoreMyinfoZzimBinding;
import com.example.ling.store.StorePurchaseActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReturnAdapter extends RecyclerView.Adapter<ReturnAdapter.ViewHolder>{

    ItemRecvStoreMyinfoReturnBinding binding;
    List<StoreReturnListVO> list;
    Context context;


    public ReturnAdapter(List<StoreReturnListVO> list, Context context) {
        this.list = list;
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
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

        String imageUrl =list.get(i).item_img;
        Picasso.get()
                .load(imageUrl)
                .into(binding.imgvItem);

        h.binding.imgvItem.setOnClickListener(v -> {
            Intent intent = new Intent(context, StorePurchaseActivity.class);
            intent.putExtra("name",list.get(i).getItem_name());
            intent.putExtra("content",list.get(i).getItem_content());
            intent.putExtra("price",list.get(i).getItem_price());
            intent.putExtra("item_code",list.get(i).getItem_code());
            intent.putExtra("item_img",list.get(i).getItem_img());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreMyinfoReturnBinding binding;


        public ViewHolder(@NonNull ItemRecvStoreMyinfoReturnBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }



}
