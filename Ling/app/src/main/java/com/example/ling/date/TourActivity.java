package com.example.ling.date;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ling.databinding.ActivityTourBinding;

public class TourActivity extends AppCompatActivity {

    ActivityTourBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTourBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}