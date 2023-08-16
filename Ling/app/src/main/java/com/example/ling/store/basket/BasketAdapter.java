package com.example.ling.store.basket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvBasketBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {


    ItemRecvBasketBinding binding;

    List<StoreBasketVO> list;

    BasketActivity activity;

    public BasketAdapter(List<StoreBasketVO> list, BasketActivity activity) {
        this.list = list;
        this.activity = activity;
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
        h.binding.tvPrice.setText("총: "+list.get(i).getItem_price()*list.get(i).getSelection()+"원");
        h.binding.tvCnt.setText(list.get(i).getSelection()+"");
        h.binding.tvPerPrice.setText(list.get(i).getItem_price()+"원");

        String imageUrl = list.get(i).getItem_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvImg);



        h.binding.imgvUp.setOnClickListener(v->{
            CommonConn conn = new CommonConn(activity,"store_up_selection");
            conn.addParamMap("basket_code",list.get(i).basket_code);
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.onExcute(((isResult, data) -> {

                CommonConn conn2 = new CommonConn(activity, "store_list_basket");
                conn2.addParamMap("id",CommonVar.loginInfo.getId());
                conn2.onExcute((isResult2, data2) -> {

                    ArrayList<StoreBasketVO> list = new Gson().fromJson(data2, new TypeToken<ArrayList<StoreBasketVO>>() {
                    }.getType());


                    Toast.makeText(activity, list.get(i).getSelection()+"개", Toast.LENGTH_SHORT).show();
                    h.binding.tvCnt.setText(list.get(i).getSelection()+"");
                    h.binding.tvPrice.setText("총: "+list.get(i).getItem_price()*list.get(i).getSelection()+"원");

                    activity.basket_total_price();


                });



            }));


//            list.get(i).setSelection(list.get(i).getSelection()+1);
//            h.binding.tvCnt.setText(list.get(i).getSelection()+"");
//            h.binding.tvPrice.setText(list.get(i).getItem_price()*list.get(i).getSelection()+"원");
//           StaticBasket.tv_total_price=list.get(i).getItem_price()*list.get(i).getSelection();

        });

        h.binding.imgvDown.setOnClickListener(v->{

            if(list.get(i).getSelection()<1){

            }else{

                CommonConn conn = new CommonConn(activity,"store_down_selection");
                conn.addParamMap("id",CommonVar.loginInfo.getId());
                conn.addParamMap("basket_code",list.get(i).basket_code);
                conn.onExcute(((isResult, data) -> {

                    CommonConn conn2 = new CommonConn(activity, "store_list_basket");
                    conn2.addParamMap("id",CommonVar.loginInfo.getId());
                    conn2.onExcute((isResult2, data2) -> {

                        ArrayList<StoreBasketVO> list = new Gson().fromJson(data2, new TypeToken<ArrayList<StoreBasketVO>>() {
                        }.getType());

                        if(list.get(i).getSelection()<1){

                            CommonConn conn3 = new CommonConn(activity,"store_up_selection");
                            conn3.addParamMap("id",CommonVar.loginInfo.getId());
                            conn3.addParamMap("basket_code",list.get(i).basket_code);
                            conn3.onExcute(((isResult3, data3) -> {

                            }));

                        }else{

                            Toast.makeText(activity, list.get(i).getSelection()+"개", Toast.LENGTH_SHORT).show();
                            h.binding.tvCnt.setText(list.get(i).getSelection()+"");
                            h.binding.tvPrice.setText("총: "+list.get(i).getItem_price()*list.get(i).getSelection()+"원");
                            activity.basket_total_price();


                        }



                    });



                }));

            }
        });

        h.binding.imgvClose.setOnClickListener(v->{

            CommonConn conn = new CommonConn(activity,"store_delete_basket");
            conn.addParamMap("basket_code",list.get(i).getBasket_code());
            conn.addParamMap("id",CommonVar.loginInfo.getId());

            conn.onExcute((isResult, data) -> {

                list.remove(i);
                notifyDataSetChanged();

                activity.basket_total_price();




            });



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
