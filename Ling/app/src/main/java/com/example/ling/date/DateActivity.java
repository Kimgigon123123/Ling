package com.example.ling.date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityDateBinding;

public class DateActivity extends AppCompatActivity {

    ActivityDateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recvTour.setAdapter(new TourItemAdapter());
        binding.recvRestaurant.setAdapter(new RestaurantItemAdapter());
        binding.recvFestival.setAdapter(new FestivalItemAdapter());
        binding.recvTour.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recvRestaurant.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recvFestival.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        binding.imgvDibs.setOnClickListener(v -> {
            Intent intent = new Intent(DateActivity.this, DibsActivity.class);
            startActivity(intent);

        });

        binding.tvTmore.setOnClickListener(v -> {

        });

        binding.tvRmore.setOnClickListener(v -> {

        });

        binding.tvFmore.setOnClickListener(v -> {

        });
    }


}