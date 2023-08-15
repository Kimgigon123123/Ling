package com.example.ling.date.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityRestaurantBinding;
import com.example.ling.date.DateInfoVO;
import com.example.ling.date.tour.TourActivity;
import com.example.ling.date.tour.TourAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    ActivityRestaurantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        restaurantList();

        binding.imgvSearch.setOnClickListener(v -> {
            CommonConn conn = new CommonConn(RestaurantActivity.this, "date_searchrest");
            conn.addParamMap("date_name", binding.edtSearch.getText().toString());
            conn.addParamMap("date_address", binding.edtSearch.getText().toString());
            conn.onExcute((isResult, data) -> {
                ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
                }.getType());
                binding.recvRestact.setAdapter(new TourAdapter(this, list));
                binding.recvRestact.setLayoutManager(new GridLayoutManager(this, 2));
            });

        });

//        binding.spnSido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                if (binding.spnSido.getSelectedItem().equals("시/도 선택")) {
//                    binding.spnSigungu.setAdapter(null);
//                    restaurantList();
//                    sggAdapter = ArrayAdapter.createFromResource(RestaurantActivity.this, R.array.empty, android.R.layout.simple_spinner_dropdown_item);
//                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    binding.spnSigungu.setAdapter(sggAdapter);
//                } else {
//                    //디비연동.
//                    CommonConn conn = new CommonConn(RestaurantActivity.this, "date_sigungu");
//                    conn.addParamMap("sido", binding.spnSido.getSelectedItem().toString());
//                    conn.onExcute((isResult, data) -> {
//                        List<String> sigungu = new Gson().fromJson(data , new TypeToken<List<String>>(){}.getType());
//                        sggAdapter = new ArrayAdapter(RestaurantActivity.this , android.R.layout.simple_spinner_dropdown_item , sigungu);
//                        sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        binding.spnSigungu.setAdapter(sggAdapter);
//                    });
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        binding.imgvRefresh.setOnClickListener(v -> {
            binding.edtSearch.setText("");
            restaurantList();
        });

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }

    public void restaurantList() {
        CommonConn conn = new CommonConn(this, "date_restaurant");
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvRestact.setAdapter(new RestaurantAdapter(this, list));
            binding.recvRestact.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }
}