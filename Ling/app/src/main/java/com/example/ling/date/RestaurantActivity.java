package com.example.ling.date;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ling.databinding.ActivityRestaurantBinding;

public class RestaurantActivity extends AppCompatActivity {

    ActivityRestaurantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}