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
        NMapFragment nMapFragment = new NMapFragment();

        Bundle bundle = new Bundle();
        bundle.putString("img", getIntent().getStringExtra("img"));
        bundle.putString("name", getIntent().getStringExtra("name"));
        bundle.putString("address", getIntent().getStringExtra("address"));
        bundle.putString("intro", getIntent().getStringExtra("intro"));
        bundle.putString("tel", getIntent().getStringExtra("tel"));
        bundle.putString("open", getIntent().getStringExtra("open"));
        bundle.putString("end", getIntent().getStringExtra("end"));
        restInfoFragment.setArguments(bundle);

        bundle.putString("lan", getIntent().getStringExtra("lan"));
        bundle.putString("lng", getIntent().getStringExtra("lng"));
        nMapFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, restInfoFragment).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, restInfoFragment).commit();
        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_restdetail, nMapFragment).commit();
        });
    }
}