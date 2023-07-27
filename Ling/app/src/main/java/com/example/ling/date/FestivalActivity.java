package com.example.ling.date;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ling.databinding.ActivityFestivalBinding;

public class FestivalActivity extends AppCompatActivity {

    ActivityFestivalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFestivalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}