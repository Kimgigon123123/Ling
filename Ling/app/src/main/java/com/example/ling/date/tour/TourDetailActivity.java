package com.example.ling.date.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityTourDetailBinding;
import com.example.ling.date.DateInfoVO;
import com.example.ling.date.NMapFragment;
import com.example.ling.date.festival.FestInfoFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

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
        NMapFragment nMapFragment = new NMapFragment();

        Bundle bundle = new Bundle();
        bundle.putString("img", getIntent().getStringExtra("img"));
        bundle.putString("name", getIntent().getStringExtra("name"));
        bundle.putString("address", getIntent().getStringExtra("address"));
        bundle.putString("intro", getIntent().getStringExtra("intro"));
        bundle.putString("open", getIntent().getStringExtra("open"));
        bundle.putString("end", getIntent().getStringExtra("end"));
        bundle.putInt("date_id", getIntent().getIntExtra("date_id", -1));
        bundle.putString("date_category_code", getIntent().getStringExtra("date_category_code"));
        tourInfoFragment.setArguments(bundle);

        bundle.putString("lan", getIntent().getStringExtra("lan"));
        bundle.putString("lng", getIntent().getStringExtra("lng"));
        nMapFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_tourdetail, tourInfoFragment).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_tourdetail, tourInfoFragment).commit();
        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_tourdetail, nMapFragment).commit();
        });
    }

}