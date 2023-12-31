package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.ling.R;
import com.example.ling.databinding.ActivityStoreMyinfoBinding;
import com.example.ling.store.basket.BasketActivity;
import com.example.ling.store.myinfo.BuylistAdapter;
import com.example.ling.store.myinfo.ReturnAdapter;
import com.example.ling.store.myinfo.ZZimActivity;

public class StoreMyinfoActivity extends AppCompatActivity {

    ActivityStoreMyinfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStoreMyinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recvZzim.setAdapter(new ZZimAdapter(this));
        binding.recvZzim.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

//        binding.recvBuylist.setAdapter(new BuylistAdapter(this));
//        binding.recvBuylist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

//        binding.recvReturn.setAdapter(new ReturnAdapter(this));
//        binding.recvReturn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnBasket.setOnClickListener(v->{
            finish();
            Intent intent = new Intent(this, BasketActivity.class);
            startActivity(intent);
        });

        binding.btnZzim.setOnClickListener(v->{
            finish();
            Intent intent = new Intent(this, ZZimActivity.class);
            startActivity(intent);
        });

        binding.btnCharge.setOnClickListener(v->{

            Intent intent = new Intent(StoreMyinfoActivity.this,ChargeCashActivity.class);

            startActivity(intent);
        });

    }

}

