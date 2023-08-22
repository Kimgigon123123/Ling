package com.example.ling.capsule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityCapsuleMainBinding;
import com.example.ling.databinding.ActivityTimeCapsuleBinding;
import com.example.ling.home.LocTrackingActivity;
import com.example.ling.store.StoreCoAdater;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CapsuleMainActivity extends AppCompatActivity {

    ActivityCapsuleMainBinding binding;

    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCapsuleMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        select();


        binding.btnAdd.setOnClickListener(v->{
            Intent intent = new Intent(this, CapsuleAddActivity.class);
            startActivity(intent);
        });

        binding.imgvBefore.setOnClickListener(v->{
            finish();
        });

        binding.imgvRight.setOnClickListener(v->{
            i=i+1;
            select();
        });

    }

    public void select() {

        CommonConn conn = new CommonConn(this, "capsule_select");
        conn.addParamMap("couple_num" , CommonVar.loginInfo.getCouple_num());
        conn.onExcute((isResult, data) -> {
            ArrayList<CapsuleVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<CapsuleVO>>() {
            }.getType());

            binding.tvTitle.setText(list.get(i).getTc_title());
            binding.tvDDay.setText(list.get(i).getD_day());
            binding.tvDate.setText(list.get(i).getTc_date());
            binding.tvIsopen.setText(list.get(i).getIsopen());

            if(binding.tvIsopen.getText().equals("개봉가능")){
                binding.tvIsopen.setTextColor(getColor(R.color.green));
            }

            binding.btnOpen.setOnClickListener(v->{
                if(binding.tvIsopen.getText().equals("개봉불가")){
                    Toast.makeText(this, "아직 개봉이 불가능합니다", Toast.LENGTH_SHORT).show();
                }else{
                    LetterDialog dialog = new LetterDialog(this,i);
                    dialog.show();
                }
            });

        });

    }
}