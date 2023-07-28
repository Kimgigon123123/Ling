package com.example.ling.join;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.databinding.ActivityJoinBinding;
import com.example.ling.databinding.FragmentJoinProfileBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class JoinActivity extends AppCompatActivity {

     ActivityJoinBinding binding;

    TabLayout tabLayout;
    ViewPager2 viewPager;
    JoinTabPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    // xml 연결
    tabLayout = findViewById(R.id.tab_layout);
    viewPager = findViewById(R.id.pager);

    // adapter 준비 및 연결
    adapter = new JoinTabPagerAdapter(this);
        viewPager.setAdapter(adapter);

    // TabLayout, ViewPager 연결
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            switch (position) {
                //binding.
            }
        }
    }).attach();

        binding.pager.setUserInputEnabled(false);




    }

    int idx = 0;

    @Override
    public void onBackPressed() {
        if (idx == 4){

            Toast.makeText(this,"이제는 짝궁과 즐거운 시간을 보낼 차례!", Toast.LENGTH_SHORT).show();
        } else if(idx != 0){
        idx --;
        changeTab(idx);

        } else{
            super.onBackPressed();
        }
    }

    public void changeTab(int idx){
        this.idx = idx;
        binding.pager.setCurrentItem(idx, true);
    }


}