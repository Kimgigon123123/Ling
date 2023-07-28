package com.example.ling.date.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.example.ling.databinding.ActivityRestaurantBinding;
import com.example.ling.date.tour.TourAdapter;

public class RestaurantActivity extends AppCompatActivity {

    ActivityRestaurantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recvRestact.setAdapter(new RestaurantAdapter());
        binding.recvRestact.setLayoutManager(new GridLayoutManager(this, 2));

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}