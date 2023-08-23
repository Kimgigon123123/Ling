package com.example.ling.date.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityDateDetailBinding;
import com.example.ling.date.map.NMapFragment;

public class DateDetailActivity extends AppCompatActivity {

    ActivityDateDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDateDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DateInfoVO vo = (DateInfoVO) getIntent().getSerializableExtra("vo" );
        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });


        DateDatailInfoFragment dateDetailFragment = new DateDatailInfoFragment();
        NMapFragment nMapFragment = new NMapFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("vo", vo);
//        bundle.putString("img", getIntent().getStringExtra("img"));
//        bundle.putString("name", getIntent().getStringExtra("name"));
//        bundle.putString("address", getIntent().getStringExtra("address"));
//        bundle.putString("intro", getIntent().getStringExtra("intro"));
//        bundle.putString("open", getIntent().getStringExtra("open"));
//        bundle.putString("end", getIntent().getStringExtra("end"));
//        bundle.putInt("date_id", getIntent().getIntExtra("date_id", -1));
//        bundle.putString("date_category_code", getIntent().getStringExtra("date_category_code"));
        dateDetailFragment.setArguments(bundle);
        nMapFragment.setArguments(bundle);

       // bundle.putString("lan", getIntent().getStringExtra("lan"));
     //   bundle.putString("lng", getIntent().getStringExtra("lng"));

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, dateDetailFragment).commit();

        binding.btnInfo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, dateDetailFragment).commit();

        });

        binding.btnMap.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ln_festdetail, nMapFragment).commit();
        });
    }
}