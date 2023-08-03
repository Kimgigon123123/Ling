package com.example.ling.date.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityFestDetailBinding;
import com.example.ling.date.InfoFragment;
import com.example.ling.date.MapFragment;

public class FestDetailActivity extends AppCompatActivity {

    ActivityFestDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFestDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, new InfoFragment()).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, new InfoFragment()).commit();
        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, new MapFragment()).commit();
        });
    }
}