package com.example.ling.date.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
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
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        restaurantList();

        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CommonConn conn = new CommonConn(RestaurantActivity.this, "date_searchrest");
                conn.addParamMap("date_name", query);
                conn.addParamMap("date_address", query);
                conn.onExcute((isResult, data) -> {
                    ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
                    }.getType());
                    binding.recvRestact.setAdapter(new TourAdapter(RestaurantActivity.this, list));
                    binding.recvRestact.setLayoutManager(new GridLayoutManager(RestaurantActivity.this, 2));
                    binding.tvNull.setVisibility(list.size()==0 ? View.VISIBLE : View.INVISIBLE);
                });
                return true;
            }
            
            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeCallbacks(runnable);
                runnable = () -> {
                    if (newText.isEmpty()) {
                        restaurantList();
                    } else {
                        CommonConn conn = new CommonConn(RestaurantActivity.this, "date_searchrest");
                        conn.addParamMap("date_name", newText);
                        conn.addParamMap("date_address", newText);
                        conn.onExcute((isResult, data) -> {
                            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
                            }.getType());
                            binding.recvRestact.setAdapter(new TourAdapter(RestaurantActivity.this, list));
                            binding.recvRestact.setLayoutManager(new GridLayoutManager(RestaurantActivity.this, 2));
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

    public void restaurantList() {
        CommonConn conn = new CommonConn(this, "date_restaurant");
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvRestact.setAdapter(new RestaurantAdapter(this, list));
            binding.recvRestact.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }
}