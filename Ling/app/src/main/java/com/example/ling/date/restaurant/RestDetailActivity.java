package com.example.ling.date.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityRestDetailBinding;
import com.example.ling.date.InfoFragment;
import com.example.ling.date.NMapFragment;

public class RestDetailActivity extends AppCompatActivity {

    ActivityRestDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, new InfoFragment()).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, new InfoFragment()).commit();
        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, new NMapFragment()).commit();
        });
    }
}