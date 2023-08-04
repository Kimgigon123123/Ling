package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityStoreMyinfoBinding;
import com.example.ling.store.ChargeCashActivity;
import com.example.ling.store.ChargeVO;
import com.example.ling.store.CompleteDialog;
import com.example.ling.store.StoreCoAdater;
import com.example.ling.store.storeCO.StoreCOVO;
import com.example.ling.store.storeCO.StorePurchaseListVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class StoreMyinfoActivity extends AppCompatActivity {




    ActivityStoreMyinfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        binding=ActivityStoreMyinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        select();
        zzimlist();
        buylist();




//






        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnCharge.setOnClickListener(v->{


            Intent intent = new Intent(StoreMyinfoActivity.this, ChargeCashActivity.class);
            startActivity(intent);
        });

        binding.imgvIntoZZim.setOnClickListener(v->{
            Intent intent = new Intent(StoreMyinfoActivity.this, ZZimActivity.class);
            startActivity(intent);
        });

        binding.imgvIntoBylist.setOnClickListener(v->{
            Intent intent = new Intent(StoreMyinfoActivity.this, ZZimActivity.class);
            intent.putExtra("buylist","buylist");
            startActivity(intent);
        });

        binding.imgvReturn.setOnClickListener(v->{
            Intent intent = new Intent(StoreMyinfoActivity.this, ZZimActivity.class);
            intent.putExtra("return","return");
            startActivity(intent);
        });

    }

    public void select(){
        CommonConn conn = new CommonConn(this,"store_myinfo");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {}.getType());


            binding.tvMoney.setText(list.get(0).getMoney()+"");
            binding.tvName.setText(list.get(0).getName());




        });
    }

    public void zzimlist() {
        CommonConn conn = new CommonConn(this, "store_list_zzim");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreZzimListVO> zzimlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreZzimListVO>>() {
            }.getType());
            binding.recvZzim.setAdapter(new ZZimAdapter(zzimlist,this));
            binding.recvZzim.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));



            binding.recvReturn.setAdapter(new ReturnAdapter(this));
            binding.recvReturn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


        });
    }

    public void buylist() {
        CommonConn conn = new CommonConn(this, "store_list_zzim");
        conn.onExcute((isResult, data) -> {
            ArrayList<StorePurchaseListVO> buylistlist = new Gson().fromJson(data, new TypeToken<ArrayList<StorePurchaseListVO>>() {
            }.getType());
            binding.recvBuylist.setAdapter(new BuylistAdapter(buylistlist, this));
            binding.recvBuylist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(ChargeVO.isCharge){
            Dialog dialog = new CompleteDialog(this,"charge");
            dialog.show();
            ChargeVO.isCharge=false;
        }
        select();

    }
}

