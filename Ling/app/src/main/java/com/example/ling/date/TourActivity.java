package com.example.ling.date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.ling.R;
import com.example.ling.databinding.ActivityTourBinding;

public class TourActivity extends AppCompatActivity {

    ActivityTourBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTourBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recvTouract.setAdapter(new TourAdapter());
        binding.recvTouract.setLayoutManager(new GridLayoutManager(this, 2));

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}