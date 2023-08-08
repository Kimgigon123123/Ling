package com.example.ling.date.dibs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityDibsBinding;
import com.example.ling.date.DateDibsVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DibsActivity extends AppCompatActivity {

    ActivityDibsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDibsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.spnDibs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(binding.spnDibs.getSelectedItem().equals("전체")) {
                    binding.recvDibs.setAdapter(new DibsAdapter(DibsActivity.this));
                    binding.recvDibs.setLayoutManager(new GridLayoutManager(DibsActivity.this, 2));


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}