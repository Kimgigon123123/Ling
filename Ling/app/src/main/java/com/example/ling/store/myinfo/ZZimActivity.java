package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ling.R;
import com.example.ling.databinding.ActivityZzimBinding;
import com.example.ling.store.FragmentAdapter;
import com.example.ling.store.basket.BasketActivity;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class ZZimActivity extends AppCompatActivity {

    ActivityZzimBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityZzimBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

        FragmentAdapter adapter = new FragmentAdapter( this, getFragmentList());
        binding.zzimRecv.setAdapter(adapter);

        binding.imgvBefore.setOnClickListener(v->{
            finish();
        });

        binding.btnBasket.setOnClickListener(v->{
            finish();
            Intent intent = new Intent(this, BasketActivity.class);
            startActivity(intent);
        });

        binding.btnMyinfo.setOnClickListener(v->{
            finish();
            Intent intent = new Intent(this, StoreMyinfoActivity.class);
            startActivity(intent);
        });

        binding.btnZzim.setOnClickListener(v->{
            finish();
            Intent intent = new Intent(this, ZZimActivity.class);
            startActivity(intent);
        });



        binding.zzimChipGrp.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {


            @Override


            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Log.d("들오온정보", "onCheckedChanged: ");

                if(checkedId==R.id.zzim_chip1){
                    binding.zzimRecv.setCurrentItem(0,true);
                }else if(checkedId==R.id.zzim_chip2){
                    binding.zzimRecv.setCurrentItem(1,true);
                }else if(checkedId==R.id.zzim_chip3){
                    binding.zzimRecv.setCurrentItem(2,true);
                }

            }
        });

        binding.zzimRecv.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {//0~3
                if(position==0){
                    binding.zzimChipGrp.check(R.id.zzim_chip1);
                }else if(position==1){
                    binding.zzimChipGrp.check(R.id.zzim_chip2);
                }else if(position==2){
                    binding.zzimChipGrp.check(R.id.zzim_chip3);
                }
            }
        });

        int selectedChipId = getIntent().getIntExtra("selected_chip", -1);
        if(selectedChipId==2){
            binding.zzimChipGrp.check(R.id.zzim_chip2);
        }else if(selectedChipId==3){
            binding.zzimChipGrp.check(R.id.zzim_chip3);
        }


    }

    ArrayList<Fragment> getFragmentList(){
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new ZZimFragment());
        list.add(new BuylistFragment());
        list.add(new ReturnFragment());


        return list;

    }
}