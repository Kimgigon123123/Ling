package com.example.ling.date.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityTourBinding;
import com.example.ling.date.DateInfoVO;
import com.example.ling.date.festival.FestivalActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TourActivity extends AppCompatActivity {

    ActivityTourBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTourBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tourList();

        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CommonConn conn = new CommonConn(TourActivity.this, "date_searchtour");
                conn.addParamMap("date_name", query);
                conn.addParamMap("date_address", query);
                conn.onExcute((isResult, data) -> {
                ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
                }.getType());
                binding.recvTouract.setAdapter(new TourAdapter(TourActivity.this, list));
                binding.recvTouract.setLayoutManager(new GridLayoutManager(TourActivity.this, 2));
            });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()) {
                    tourList();
                } else {
                    CommonConn conn = new CommonConn(TourActivity.this, "date_searchtour");
                    conn.addParamMap("date_name", newText);
                    conn.addParamMap("date_address", newText);
                    conn.onExcute((isResult, data) -> {
                        ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
                        }.getType());
                        binding.recvTouract.setAdapter(new TourAdapter(TourActivity.this, list));
                        binding.recvTouract.setLayoutManager(new GridLayoutManager(TourActivity.this, 2));
                    });
                }
                return true;
            }
        });

//        binding.imgvSearch.setOnClickListener(v -> {
//            CommonConn conn = new CommonConn(TourActivity.this, "date_searchtour");
//            conn.addParamMap("date_name", binding.edtSearch.getText().toString());
//            conn.addParamMap("date_address", binding.edtSearch.getText().toString());
//            conn.onExcute((isResult, data) -> {
//                ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
//                }.getType());
//                binding.recvTouract.setAdapter(new TourAdapter(this, list));
//                binding.recvTouract.setLayoutManager(new GridLayoutManager(this, 2));
//            });
//
//        });

//        binding.imgvRefresh.setOnClickListener(v -> {
//            binding.edtSearch.setText("");
//            tourList();
//        });

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }

    public void tourList() {
        CommonConn conn = new CommonConn(this, "date_tour");
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvTouract.setAdapter(new TourAdapter(this, list));
            binding.recvTouract.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }
}