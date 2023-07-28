package com.example.ling.date.dibs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityDibsDetailBinding;

public class DibsDetailActivity extends AppCompatActivity {

    ActivityDibsDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDibsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvInfo.setText("[INFO]");
        binding.tvDetailinfo.setText("상세정보가 나온다");

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}