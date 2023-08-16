package com.example.ling.date.dibs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ling.R;
import com.example.ling.databinding.ActivityDibsDetailBinding;
import com.example.ling.date.NMapFragment;
import com.example.ling.date.festival.FestInfoFragment;


public class DibsDetailActivity extends AppCompatActivity {

    ActivityDibsDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDibsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        DibsInfoFragment dibsInfoFragment = new DibsInfoFragment();
        NMapFragment nMapFragment = new NMapFragment();

        Bundle bundle = new Bundle();
        bundle.putString("img", getIntent().getStringExtra("img"));
        bundle.putString("name", getIntent().getStringExtra("name"));
        bundle.putString("address", getIntent().getStringExtra("address"));
        bundle.putString("intro", getIntent().getStringExtra("intro"));
        bundle.putString("open", getIntent().getStringExtra("open"));
        bundle.putString("end", getIntent().getStringExtra("end"));
        bundle.putString("code", getIntent().getStringExtra("code"));
        dibsInfoFragment.setArguments(bundle);

        bundle.putString("lan", getIntent().getStringExtra("lan"));
        bundle.putString("lng", getIntent().getStringExtra("lng"));
        nMapFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_dibsdetail, dibsInfoFragment).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_dibsdetail, dibsInfoFragment).commit();
        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_dibsdetail, nMapFragment).commit();
        });

//        if(binding.btnInfo.isChecked()) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.ln_detail, new InfoFragment()).commit();
//        }else if(binding.btnMap.isChecked()) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.ln_detail, new MapFragment()).commit();
//        }
    }
}