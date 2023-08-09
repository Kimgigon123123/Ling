package com.example.ling.store.basket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityBasketBinding;
import com.example.ling.databinding.ActivityChargeCashBinding;
import com.example.ling.store.ChargeVO;
import com.example.ling.store.CompleteDialog;
import com.example.ling.store.StorePaymentActivity;
import com.example.ling.store.StorePurchaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;



public class BasketActivity extends AppCompatActivity {

    ActivityBasketBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityBasketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        binding.recvBasket.setAdapter(new BasketAdapter(this));
//        binding.recvBasket.setLayoutManager(new LinearLayoutManager(this));


        CommonConn conn = new CommonConn(this, "store_list_basket");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreBasketVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreBasketVO>>() {
            }.getType());


            binding.recvBasket.setAdapter(new BasketAdapter(list,this  ));
            binding.recvBasket.setLayoutManager(new LinearLayoutManager(this));

        });

        basket_total_price();





        binding.imgvBefore.setOnClickListener(v->{
            finish();
        });

        binding.btnBuy.setOnClickListener(v -> {
            finish();


            CommonConn conn2 = new CommonConn(this, "store_basket_totalprice");
            conn2.onExcute((isResult, data) -> {

                ArrayList<StoreBasketVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreBasketVO>>() {
                }.getType());

                Intent intent = new Intent(this, StorePaymentActivity.class);
                intent.putExtra("basket_total_price",list.get(0).getTotal_price());
                startActivity(intent);



            });



        });



    }

    protected void onRestart() {
        super.onRestart();

        binding.tvTotalPrice.setText(StaticBasket.tv_total_price+"원");


    }

    public void basket_total_price(){
        CommonConn conn = new CommonConn(this, "store_basket_totalprice");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreBasketVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreBasketVO>>() {
            }.getType());
                    if(list==null){

                    }
                    else{
                        if(list.get(0)==null){
                            binding.tvTotalPrice.setText("0원");

                        }else{
                                binding.tvTotalPrice.setText(list.get(0).getTotal_price()+"원");


                        }


                    }


        });
    }
}