package com.example.ling.date.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityFestDetailBinding;

public class FestDetailActivity extends AppCompatActivity {

    ActivityFestDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFestDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.tvInfo.setText("[INFO]");
        binding.tvDetailinfo.setText("상세정보가 나온다");

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}