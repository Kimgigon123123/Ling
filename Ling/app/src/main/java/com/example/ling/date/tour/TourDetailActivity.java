package com.example.ling.date.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.ling.R;
import com.example.ling.databinding.ActivityTourDetailBinding;

public class TourDetailActivity extends AppCompatActivity {

    ActivityTourDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTourDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvInfo.setText("[INFO]");
        binding.tvDetailinfo.setText("상세정보가 나온다");

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}