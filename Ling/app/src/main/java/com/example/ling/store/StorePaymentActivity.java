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
        select();



        if(ChargeVO.isCharge){
            Dialog dialog = new CompleteDialog(this,"charge");
            dialog.show();
            ChargeVO.isCharge=false;
        }


        Intent intent2 = getIntent();
        int price = intent2.getIntExtra("price",0);
        binding.tvPrice.setText(price+"원");

        totalPrice = price+3000;
        binding.tvTotalPrice.setText(totalPrice+"원");

        binding.imgvBefore.setOnClickListener(v -> {

          finish();


        });

        binding.btnBuy.setOnClickListener(v->{


            CommonConn conn = new CommonConn(this,"store_myinfo");
            conn.onExcute((isResult, data) -> {

                ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {}.getType());

                if(totalPrice > list.get(0).getMoney()){
                    NoMoneyDialog dialog = new NoMoneyDialog(this);
                    dialog.show();
                }else {
                    buy();
                    ChargeVO.isBuy=true;
                    finish();



                }


            });



        });

        binding.imgvIntoAdrress.setOnClickListener(v->{
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


        });
    }


}