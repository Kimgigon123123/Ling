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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FestivalActivity extends AppCompatActivity {

    ActivityFestivalBinding binding;
    ArrayAdapter<CharSequence> sdAdapter, sggAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFestivalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recvFestact.setAdapter(new FestivalAdapter(this));
        binding.recvFestact.setLayoutManager(new GridLayoutManager(this, 2));

        sdAdapter = ArrayAdapter.createFromResource(this, R.array.sido, android.R.layout.simple_spinner_dropdown_item);
        sdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnSido.setAdapter(sdAdapter);

        binding.spnSido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (binding.spnSido.getSelectedItem().equals("시/도 선택")) {
                    binding.spnSigungu.setAdapter(null);
                } else if (binding.spnSido.getSelectedItem().equals("서울특별시")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.seoul_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("부산광역시")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.busan_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("대구광역시")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.daegu_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("인천광역시")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.incheon_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("광주광역시")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.gwangju_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("대전광역시")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.daejeon_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("울산광역시")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.ulsan_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("세종특별자치시")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.sejong_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("경기도")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.gyeongi_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("강원도")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.gangwon_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("충청북도")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.chungbuk_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("충청남도")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.chungnam_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("전라북도")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.jeonbuk_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("전라남도")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.jeonnam_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("경상북도")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.gyeongbuk_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else if (binding.spnSido.getSelectedItem().equals("경상남도")) {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.gyeongnam_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                } else {
                    sggAdapter = ArrayAdapter.createFromResource(FestivalActivity.this, R.array.jeju_sigungu, android.R.layout.simple_spinner_dropdown_item);
                    sggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnSigungu.setAdapter(sggAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        CommonConn conn = new CommonConn(this, "date_sido");
//        conn.onExcute((isResult, data) -> {
//            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
//            ArrayAdapter<DateInfoVO> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
//            binding.spnSido.setAdapter(adapter);
//
//        });

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

    }


}