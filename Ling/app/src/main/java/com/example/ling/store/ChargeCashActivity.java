package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityChargeCashBinding;
import com.example.ling.databinding.ActivityStorePaymentBinding;
import com.example.ling.store.myinfo.StoreMyinfoVO;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ChargeCashActivity extends AppCompatActivity {

    ActivityChargeCashBinding binding;

    String intoPayment=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding= ActivityChargeCashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        select();






        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnInputBank.setOnClickListener(v -> {

            BottomSheetDialog bottomSheetDialog = new BankDialog(this);
            bottomSheetDialog.show();


        });

        binding.btnCharge.setOnClickListener(v -> {


            try {
                CommonConn conn = new CommonConn(this,"store_charge");
                conn.addParamMap("edtMoney",Integer.parseInt(binding.edtMoney.getText().toString()));//"0" -> 0 : ""->
                conn.onExcute((isResult, data) -> {


                });

                ChargeVO.isCharge=true;
                finish();


            }catch (Exception e){
                Toast.makeText(this, "다시입력", Toast.LENGTH_SHORT).show();
            }

        });

    }


    public void select(){
        CommonConn conn = new CommonConn(this,"store_myinfo");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {}.getType());


            binding.tvMoney.setText(list.get(0).getMoney()+"");


        });
    }



}