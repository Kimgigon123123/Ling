package com.example.ling.date.festival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityFestivalBinding;
import com.example.ling.date.DateInfoVO;
import com.example.ling.date.restaurant.RestaurantActivity;
import com.example.ling.date.tour.TourActivity;
import com.example.ling.date.tour.TourAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FestivalActivity extends AppCompatActivity {

    ActivityFestivalBinding binding;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFestivalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        festivalList();

        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CommonConn conn = new CommonConn(FestivalActivity.this, "date_searchfest");
                conn.addParamMap("date_name", query);
                conn.addParamMap("date_address", query);
                conn.onExcute((isResult, data) -> {
                    ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
                    }.getType());
                    binding.recvFestact.setAdapter(new TourAdapter(FestivalActivity.this, list));
                    binding.recvFestact.setLayoutManager(new GridLayoutManager(FestivalActivity.this, 2));
                    binding.tvNull.setVisibility(list.size()==0 ? View.VISIBLE : View.INVISIBLE);
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeCallbacks(runnable);
                runnable = () -> {
                    if (newText.isEmpty()) {
                        festivalList();
                    } else {
                        CommonConn conn = new CommonConn(FestivalActivity.this, "date_searchfest");
                        conn.addParamMap("date_name", newText);
                        conn.addParamMap("date_address", newText);
                        conn.onExcute((isResult, data) -> {
                            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
                            }.getType());
                            binding.recvFestact.setAdapter(new TourAdapter(FestivalActivity.this, list));
                            binding.recvFestact.setLayoutManager(new GridLayoutManager(FestivalActivity.this, 2));
                            binding.tvNull.setVisibility(list.size() == 0 ? View.VISIBLE : View.INVISIBLE);
                        });
                    }
                };
                handler.postDelayed(runnable, 400);
                return true;
            }
        });

//        sdAdapter = ArrayAdapter.createFromResource(this, R.array.sido, android.R.layout.simple_spinner_dropdown_item);
//        sdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.spnSido.setAdapter(sdAdapter);
//
//        binding.spnSido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                if (binding.spnSido.getSelectedItem().equals("시/도 선택")) {
//                    binding.spnSigungu.setAdapter(null);
//
//                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.empty, android.R.layout.simple_spinner_dropdown_item);
//                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    binding.spnSigungu.setAdapter(sggAdapter);
//                }else{
//                    //디비연동.
//                    CommonConn conn = new CommonConn(FestivalActivity.this , "date_sigungu");
//                    conn.addParamMap("sido" , binding.spnSido.getSelectedItem().toString());
//                    conn.onExcute((isResult, data) -> {
//                        List<String> tempList = new Gson().fromJson(data, new TypeToken<List<String>>(){}.getType());
//                        sggAdapter = new ArrayAdapter(FestivalActivity.this, android.R.layout.simple_spinner_dropdown_item, tempList);
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

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }

    public void festivalList() {
        CommonConn conn = new CommonConn(this, "date_festival");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvFestact.setAdapter(new FestivalAdapter(this, list));
            binding.recvFestact.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }
}