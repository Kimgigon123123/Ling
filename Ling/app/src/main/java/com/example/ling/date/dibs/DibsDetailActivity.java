package com.example.ling.date.dibs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ling.R;
import com.example.ling.databinding.ActivityDibsDetailBinding;
import com.example.ling.date.InfoFragment;
import com.example.ling.date.NMapFragment;


public class DibsDetailActivity extends AppCompatActivity {

    ActivityDibsDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDibsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_dibsdetail, new InfoFragment()).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_dibsdetail, new InfoFragment()).commit();
        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_dibsdetail, new NMapFragment()).commit();
        });

//        if(binding.btnInfo.isChecked()) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.ln_detail, new InfoFragment()).commit();
//        }else if(binding.btnMap.isChecked()) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.ln_detail, new MapFragment()).commit();
//        }
    }
}