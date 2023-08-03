package com.example.ling.store.basket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityBasketBinding;
import com.example.ling.databinding.ActivityChargeCashBinding;
import com.example.ling.store.StorePurchaseActivity;

public class BasketActivity extends AppCompatActivity {

    ActivityBasketBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityBasketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recvBasket.setAdapter(new BasketAdapter(this));
        binding.recvBasket.setLayoutManager(new LinearLayoutManager(this));



        binding.imgvBefore.setOnClickListener(v->{
            finish();
        });

        binding.btnBuy.setOnClickListener(v -> {
            finish();
                Intent intent = new Intent(this, StorePurchaseActivity.class);
                startActivity(intent);

        });



    }
}