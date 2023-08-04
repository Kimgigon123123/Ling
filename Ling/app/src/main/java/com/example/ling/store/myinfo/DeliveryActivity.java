package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityBasketBinding;
import com.example.ling.databinding.ActivityDeliveryBinding;
import com.example.ling.store.BuyDialog;
import com.example.ling.store.StorePurchaseActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DeliveryActivity extends AppCompatActivity {

    ActivityDeliveryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDeliveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnBuy.setOnClickListener(v->{
            BottomSheetDialog bottomSheetDialog = new BuyDialog(this,"이름",1000,"1");
            bottomSheetDialog.show();
        });

        binding.btnReturn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReturnActivity.class);
            startActivity(intent);
        });


    }
}