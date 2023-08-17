package com.example.ling.date.dibs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityDibsBinding;
import com.example.ling.date.DateDibsVO;
import com.example.ling.date.DateInfoVO;
import com.google.android.gms.common.internal.service.Common;
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
//                    binding.recvDibs.setAdapter(new DibsAdapter(DibsActivity.this));
//                    binding.recvDibs.setLayoutManager(new GridLayoutManager(DibsActivity.this, 2));
                    selectAll();
                } else if(binding.spnDibs.getSelectedItem().equals("여행")) {
                    selectTour();
                } else if(binding.spnDibs.getSelectedItem().equals("맛집")) {
                    selectRestaurant();
                } else {
                    selectFestival();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void selectAll() {
        CommonConn conn = new CommonConn(this, "date_alldibs");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<DateDibsVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateDibsVO>>(){}.getType());
            binding.recvDibs.setAdapter(new DibsAdapter(this, list));
            binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }

    public void selectTour() {
        CommonConn conn = new CommonConn(this, "date_tourdibs");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<DateDibsVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateDibsVO>>(){}.getType());
            binding.recvDibs.setAdapter(new DibsAdapter(this, list));
            binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }

    public void selectRestaurant() {
        CommonConn conn = new CommonConn(this, "date_restaurantdibs");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<DateDibsVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateDibsVO>>(){}.getType());
            binding.recvDibs.setAdapter(new DibsAdapter(this, list));
            binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }

    public void selectFestival() {
        CommonConn conn = new CommonConn(this, "date_festivaldibs");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<DateDibsVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateDibsVO>>(){}.getType());
            binding.recvDibs.setAdapter(new DibsAdapter(this, list));
            binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }

}