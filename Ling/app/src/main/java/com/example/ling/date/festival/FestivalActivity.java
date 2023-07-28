package com.example.ling.date.festival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.example.ling.databinding.ActivityFestivalBinding;

public class FestivalActivity extends AppCompatActivity {

    ActivityFestivalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFestivalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recvFestact.setAdapter(new FestivalAdapter(this));
        binding.recvFestact.setLayoutManager(new GridLayoutManager(this, 2));

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}