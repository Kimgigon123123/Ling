package com.example.ling.store.basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvBasketBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {


    ItemRecvBasketBinding binding;

    List<StoreBasketVO> list;
    Context context;



    public BasketAdapter(List<StoreBasketVO> list, Context context) {
        this.list = list;
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
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.tvName.setText(list.get(i).getItem_name());
        h.binding.tvPrice.setText(list.get(i).getItem_price()+"원");

        String imageUrl = list.get(i).getItem_img();
        Picasso.get()
                .load(imageUrl)
                .into(binding.imgvImg);



        h.binding.imgvUp.setOnClickListener(v->{
            list.get(i).setSelection(list.get(i).getSelection()+1);
            h.binding.tvCnt.setText(list.get(i).getSelection()+"");
            h.binding.tvPrice.setText(list.get(i).getItem_price()*list.get(i).getSelection()+"원");
           StaticBasket.tv_total_price=list.get(i).getItem_price()*list.get(i).getSelection();

        });

        h.binding.imgvDown.setOnClickListener(v->{
//            cnt=cnt-1;
//            h.binding.tvCnt.setText(cnt+"");
//            h.binding.tvPrice.setText(list.get(i).getItem_price()*cnt+"원");

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvBasketBinding binding;

        public ViewHolder(@NonNull ItemRecvBasketBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
