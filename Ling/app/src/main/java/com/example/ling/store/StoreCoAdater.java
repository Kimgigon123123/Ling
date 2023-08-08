package com.example.ling.store;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ItemRecvDibsBinding;
import com.example.ling.databinding.ItemRecvStoreCoBinding;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


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

        String imageUrl=list.get(i).getItem_img();
                                 Picasso.get()
                                .load(imageUrl)
                                .into(binding.imgvItem);

        h.binding.intoItem.setOnClickListener(v -> {
            Intent intent = new Intent(context,StorePurchaseActivity.class);
            intent.putExtra("name",list.get(i).getItem_name());
            intent.putExtra("content",list.get(i).getItem_content());
            intent.putExtra("price",list.get(i).getItem_price());
            intent.putExtra("item_code",list.get(i).getItem_code());
            intent.putExtra("item_img",list.get(i).getItem_img());
            intent.putExtra("category_code",list.get(i).getCategory_code());

                    CommonConn conn = new CommonConn(context,"store_popular_up");
                    conn.addParamMap("item_code" ,list.get(i).getItem_code());

                    conn.onExcute((isResult, data) -> {


                    });




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
