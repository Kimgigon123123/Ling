package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityStorePaymentBinding;
import com.example.ling.databinding.ActivityStorePurchaseBinding;

public class StorePaymentActivity extends AppCompatActivity {

    ActivityStorePaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityStorePaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnBack.setOnClickListener(v -> {

            Intent intent = new Intent (StorePaymentActivity.this,StorePurchaseActivity.class);
            startActivity(intent);

        });

        binding.btnBuy.setOnClickListener(v->{

            NoMoneyDialog dialog = new NoMoneyDialog(this);
            dialog.show();


        });
    }
}