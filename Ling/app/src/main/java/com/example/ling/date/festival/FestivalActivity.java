package com.example.ling.date.festival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityFestivalBinding;
import com.example.ling.date.DateInfoVO;
import com.example.ling.date.tour.TourActivity;
import com.example.ling.date.tour.TourAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FestivalActivity extends AppCompatActivity {

    ActivityFestivalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFestivalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        festivalList();

        binding.imgvSearch.setOnClickListener(v -> {
            CommonConn conn = new CommonConn(FestivalActivity.this, "date_searchfest");
            conn.addParamMap("date_name", binding.edtSearch.getText().toString());
            conn.addParamMap("date_address", binding.edtSearch.getText().toString());
            conn.onExcute((isResult, data) -> {
                ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>() {
                }.getType());
                binding.recvFestact.setAdapter(new TourAdapter(this, list));
                binding.recvFestact.setLayoutManager(new GridLayoutManager(this, 2));
            });

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

        binding.imgvRefresh.setOnClickListener(v -> {
            binding.edtSearch.setText("");
            festivalList();
        });

    }
    public void festivalList() {
        CommonConn conn = new CommonConn(this, "date_festival");
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvFestact.setAdapter(new FestivalAdapter(this, list));
            binding.recvFestact.setLayoutManager(new GridLayoutManager(this, 2));
        });
    }

}