package com.example.ling.date.festival;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityFestDetailBinding;
import com.example.ling.date.NMapFragment;

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


        FestInfoFragment festInfoFragment = new FestInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("img", getIntent().getIntExtra("img", -1));
        bundle.putString("name", getIntent().getStringExtra("name"));
        bundle.putString("address", getIntent().getStringExtra("address"));
        bundle.putString("intro", getIntent().getStringExtra("intro"));
        bundle.putString("open", getIntent().getStringExtra("open"));
        bundle.putString("end", getIntent().getStringExtra("end"));
        festInfoFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, festInfoFragment).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, festInfoFragment).commit();

        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, new NMapFragment()).commit();
        });
    }
}