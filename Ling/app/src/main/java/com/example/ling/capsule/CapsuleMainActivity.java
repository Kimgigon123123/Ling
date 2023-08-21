package com.example.ling.capsule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityCapsuleMainBinding;
import com.example.ling.databinding.ActivityTimeCapsuleBinding;
import com.example.ling.home.LocTrackingActivity;

public class CapsuleMainActivity extends AppCompatActivity {

    ActivityCapsuleMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCapsuleMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(v->{
            Intent intent = new Intent(this, CapsuleAddActivity.class);
            startActivity(intent);
        });

        binding.imgvBefore.setOnClickListener(v->{
            finish();
        });


    }
}