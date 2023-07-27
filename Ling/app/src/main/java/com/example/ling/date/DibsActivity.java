package com.example.ling.date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityDibsBinding;

public class DibsActivity extends AppCompatActivity {

    ActivityDibsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDibsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recvDibs.setAdapter(new DibsAdapter());
        binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }
}