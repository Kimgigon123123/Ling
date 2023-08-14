package com.example.ling.store.myinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvStoreMyinfoBuylistBinding;
import com.example.ling.databinding.ItemRecvStoreMyinfoZzimBinding;
import com.example.ling.store.StorePurchaseActivity;
import com.example.ling.store.storeCO.StorePurchaseListVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BuylistAdapter extends RecyclerView.Adapter<BuylistAdapter.ViewHolder>{

    ItemRecvStoreMyinfoBuylistBinding binding;
    Context context;
    ArrayList<StorePurchaseListVO> list;

    public BuylistAdapter(ArrayList<StorePurchaseListVO> list,Context context) {
        this.list=list;
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

        h.binding.tvName.setText(list.get(i).getItem_name());

        String imageUrl =list.get(i).getItem_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvItem);

//        h.binding.imgvItem.setOnClickListener(v -> {
//            Intent intent = new Intent(context,StorePurchaseActivity.class);
//            intent.putExtra("name",list.get(i).getItem_name());
//            intent.putExtra("content",list.get(i).getItem_content());
//            intent.putExtra("price",list.get(i).getItem_price());
//            intent.putExtra("item_code",list.get(i).getItem_code());
//            intent.putExtra("item_img",list.get(i).getItem_img());
//            intent.putExtra("category_code",list.get(i).getCategory_code());
//            Toast.makeText(context, list.get(i).getItem_code(), Toast.LENGTH_SHORT).show();
//
//
//            context.startActivity(intent);
//        });



        h.binding.imgvItem.setOnClickListener(v -> {
            Intent intent = new Intent(context, DeliveryActivity.class);
            intent.putExtra("order_num",list.get(i).getOrder_num());
            context.startActivity(intent);
        });
//
//        h.binding.tvReturn.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ReturnActivity.class);
//            intent.putExtra("order_num",list.get(i).getOrder_num());
//            context.startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreMyinfoBuylistBinding binding;


        public ViewHolder(@NonNull ItemRecvStoreMyinfoBuylistBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }



}
