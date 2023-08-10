package com.example.ling.date.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityRestDetailBinding;
import com.example.ling.date.NMapFragment;
import com.example.ling.date.festival.FestInfoFragment;

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

        RestInfoFragment restInfoFragment = new RestInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("img", getIntent().getIntExtra("img", -1));
        bundle.putString("name", getIntent().getStringExtra("name"));
        bundle.putString("address", getIntent().getStringExtra("address"));
        bundle.putString("intro", getIntent().getStringExtra("intro"));
        bundle.putString("open", getIntent().getStringExtra("open"));
        bundle.putString("end", getIntent().getStringExtra("end"));
        restInfoFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, restInfoFragment).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, restInfoFragment).commit();
        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, new NMapFragment()).commit();
        });
    }
}