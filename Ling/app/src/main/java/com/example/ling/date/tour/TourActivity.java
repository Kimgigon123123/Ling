package com.example.ling.date.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
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
    private Handler handler = new Handler();
    private Runnable runnable;

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
                    binding.tvNull.setVisibility(list.size() == 0 ? View.VISIBLE : View.INVISIBLE);
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeCallbacks(runnable);
                runnable = () -> {
                    if (newText.isEmpty()) {
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
                            binding.tvNull.setVisibility(list.size() == 0 ? View.VISIBLE : View.INVISIBLE);
                        });
                    }
                };
                handler.postDelayed(runnable, 400);
                return true;
            }
        });

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }

    public void tourList() {
        CommonConn conn = new CommonConn(this, "date_tour");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
            }.getType());
            binding.recvTouract.setAdapter(new TourAdapter(this, list));
            binding.recvTouract.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }
}