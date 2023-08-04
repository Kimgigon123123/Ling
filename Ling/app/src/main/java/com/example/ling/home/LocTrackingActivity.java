package com.example.ling.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.calendar.CalendarActivity;
import com.example.ling.databinding.ActivityLocTrackingBinding;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.util.FusedLocationSource;

public class LocTrackingActivity extends AppCompatActivity {

    ActivityLocTrackingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocTrackingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvHome.setOnClickListener(v -> {
            Intent intent = new Intent(LocTrackingActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

}