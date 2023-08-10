package com.example.ling.date.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityTourDetailBinding;
import com.example.ling.date.NMapFragment;
import com.example.ling.date.festival.FestInfoFragment;

public class TourDetailActivity extends AppCompatActivity {

    ActivityTourDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTourDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        TourInfoFragment tourInfoFragment = new TourInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("img", getIntent().getIntExtra("img", -1));
        bundle.putString("name", getIntent().getStringExtra("name"));
        bundle.putString("address", getIntent().getStringExtra("address"));
        bundle.putString("intro", getIntent().getStringExtra("intro"));
        bundle.putString("open", getIntent().getStringExtra("open"));
        bundle.putString("end", getIntent().getStringExtra("end"));
        tourInfoFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_tourdetail, tourInfoFragment).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_tourdetail, tourInfoFragment).commit();
        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_tourdetail, new NMapFragment()).commit();
        });
    }
}