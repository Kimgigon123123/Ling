package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.ling.databinding.ActivityStorePaymentBinding;
import com.example.ling.store.myinfo.AddressActivity;

public class StorePaymentActivity extends AppCompatActivity {

    ActivityStorePaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityStorePaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if(ChargeVO.isCharge){
            Dialog dialog = new CompleteDialog(this,"charge");
            dialog.show();
            ChargeVO.isCharge=false;
        }



        binding.imgvBefore.setOnClickListener(v -> {

          finish();


        });

        binding.btnBuy.setOnClickListener(v->{

            NoMoneyDialog dialog = new NoMoneyDialog(this);
            dialog.show();


        });

        binding.imgvIntoAdrress.setOnClickListener(v->{
            Intent intent = new Intent(StorePaymentActivity.this, AddressActivity.class);
            startActivity(intent);
        });
    }
}