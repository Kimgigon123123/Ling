package com.example.ling.date.dibs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityDibsBinding;
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
                   selectDibs("");
                } else if(binding.spnDibs.getSelectedItem().equals("여행")) {
                    selectDibs("TO");
                } else if(binding.spnDibs.getSelectedItem().equals("맛집")) {
                    selectDibs("RE");
                } else {
                    selectDibs("FE");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
//
//    public void selectAll() {
//        CommonConn conn = new CommonConn(this, "date_alldibs");
//        conn.addParamMap("id", CommonVar.loginInfo.getId());
//        conn.onExcute((isResult, data) -> {
//            ArrayList<DateDibsVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateDibsVO>>(){}.getType());
//            binding.recvDibs.setAdapter(new DibsAdapter(this, list));
//            binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));
//            binding.tvNull.setVisibility(list.size()==0 ? View.VISIBLE : View.INVISIBLE);
//        });
//    }
//
//    public void selectTour() {
//        CommonConn conn = new CommonConn(this, "date_tourdibs");
//        conn.addParamMap("id", CommonVar.loginInfo.getId());
//        conn.onExcute((isResult, data) -> {
//            ArrayList<DateDibsVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateDibsVO>>(){}.getType());
//            binding.recvDibs.setAdapter(new DibsAdapter(this, list));
//            binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));
//            binding.tvNull.setVisibility(list.size()==0 ? View.VISIBLE : View.INVISIBLE);
//        });
//    }
//
//    public void selectRestaurant() {
//        CommonConn conn = new CommonConn(this, "date_restaurantdibs");
//        conn.addParamMap("id", CommonVar.loginInfo.getId());
//        conn.onExcute((isResult, data) -> {
//            ArrayList<DateDibsVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateDibsVO>>(){}.getType());
//            binding.recvDibs.setAdapter(new DibsAdapter(this, list));
//            binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));
//            binding.tvNull.setVisibility(list.size()==0 ? View.VISIBLE : View.INVISIBLE);
//        });
//    }
//
//    public void selectFestival() {
//
//    }

    public void selectDibs(String category){
        CommonConn conn = new CommonConn(this, "date_selectdibs");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.addParamMap("category", category);
        conn.onExcute((isResult, data) -> {
            ArrayList<DateDibsVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateDibsVO>>(){}.getType());
            binding.recvDibs.setAdapter(new DibsAdapter(this, list));
            binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));
            binding.tvNull.setVisibility(list.size()==0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

}