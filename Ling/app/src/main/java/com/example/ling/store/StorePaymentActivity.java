package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityStorePaymentBinding;
import com.example.ling.store.myinfo.AddressActivity;
import com.example.ling.store.myinfo.StoreMyinfoVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class StorePaymentActivity extends AppCompatActivity {

    ActivityStorePaymentBinding binding;

    int totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityStorePaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent3 = getIntent();
        int basket_total_price = intent3.getIntExtra("basket_total_price",0);

        if(basket_total_price != 0){
            binding.tvPrice.setText(basket_total_price+"원");
            binding.tvTotalPrice.setText(basket_total_price+3000+"원");
        }

        else {

            Intent intent2 = getIntent();
            int price = intent2.getIntExtra("price", 0);
            binding.tvPrice.setText(price + "원");

            totalPrice = price + 3000;
            binding.tvTotalPrice.setText(totalPrice + "원");

        }

        select();


        if (ChargeVO.isCharge) {
            Dialog dialog = new CompleteDialog(this, "charge");
            dialog.show();
            ChargeVO.isCharge = false;
        }


        binding.imgvBefore.setOnClickListener(v -> {

            finish();


        });

        binding.btnBuy.setOnClickListener(v -> {


            CommonConn conn = new CommonConn(this, "store_myinfo");
            conn.onExcute((isResult, data) -> {

                ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {
                }.getType());

                if (totalPrice > list.get(0).getMoney()) {
                    NoMoneyDialog dialog = new NoMoneyDialog(this);
                    dialog.show();
                } else {
                    buy();
                    ChargeVO.isBuy = true;
                    finish();


                }


            });


        });

        binding.imgvIntoAdrress.setOnClickListener(v -> {
            Intent intent = new Intent(StorePaymentActivity.this, AddressActivity.class);
            startActivity(intent);
        });
    }

    protected void onRestart() {
        super.onRestart();

        if(ChargeVO.isCharge){
            Dialog dialog = new CompleteDialog(this,"charge");
            dialog.show();
            ChargeVO.isCharge=false;
        }


        CommonConn conn = new CommonConn(this, "store_myinfo");


        conn.onExcute((isResult, data) -> {
            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {
            }.getType());


            binding.tvMymoney.setText(list.get(0).getMoney() + "");

        });
    }

    public void select(){
        CommonConn conn = new CommonConn(this, "store_myinfo");


        conn.onExcute((isResult, data) -> {
            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {}.getType());


            binding.tvMymoney.setText(list.get(0).getMoney()+"");

    });
}

    public void buy(){
        CommonConn conn = new CommonConn(this, "store_buy");
        conn.addParamMap("totalPrice",totalPrice);
        conn.onExcute((isResult, data) -> {


            Intent intent = getIntent();
            String item_code = intent.getStringExtra("item_code");
            int cnt = intent.getIntExtra("cnt",0);

            CommonConn conn2 = new CommonConn(this , "insert_purchase");
            conn2.addParamMap("item_code" , item_code);
            conn2.addParamMap("purchase_cnt" , cnt);
            conn2.onExcute((isResult2, data2) -> {

            });

        });
    }


}