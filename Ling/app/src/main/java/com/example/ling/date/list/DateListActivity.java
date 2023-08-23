package com.example.ling.date.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityDateListBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DateListActivity extends AppCompatActivity {

    ActivityDateListBinding binding;
    private Handler handler = new Handler();
    private Runnable runnable;

    private String date_category_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDateListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        date_category_code = getIntent().getStringExtra("category");





        datelList("");

        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                datelList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeCallbacks(runnable);
                runnable = () -> {
                    datelList(newText);
                };
                handler.postDelayed(runnable, 400);
                return true;
            }
        });

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });
    }

    public void datelList(String query) {
        CommonConn conn = new CommonConn(this, "date_SelectList");

        conn.addParamMap("date_category_code" , date_category_code);
        conn.addParamMap("date_name" , query);
        conn.addParamMap("date_address", query);
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvFestact.setAdapter(new DateListItemAdapter(this, list));
            binding.recvFestact.setLayoutManager(new GridLayoutManager(this, 2));
            binding.tvNull.setVisibility(list.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //festivalList();
    }
}