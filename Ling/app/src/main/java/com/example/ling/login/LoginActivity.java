package com.example.ling.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.databinding.ActivityJoinBinding;
import com.example.ling.databinding.ActivityLoginBinding;
import com.example.ling.join.JoinTabPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    int idx = 0;
    public boolean isFindCheck = true;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    LoginTabPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // xml 연결
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);

        // adapter 준비 및 연결
        adapter = new LoginTabPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // TabLayout, ViewPager 연결
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {

                }
            }
        }).attach();

        binding.pager.setUserInputEnabled(false);




    }

    @Override
    public void onBackPressed() {
        if (idx == 4){

            Toast.makeText(this,"이제는 짝궁과 즐거운 시간을 보낼 차례!", Toast.LENGTH_SHORT).show();
        } else if(idx != 0){
            idx --;
            find_changeTab(idx);

        } else{
            super.onBackPressed();
        }
    }
    public void find_changeTab(int idx){
        this.idx = idx;

        if(idx==2) {
            adapter = new LoginTabPagerAdapter(this);
            viewPager.setAdapter(adapter);

            // TabLayout, ViewPager 연결
            new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    switch (position) {

                    }
                }
            }).attach();
        }
        binding.pager.setCurrentItem(idx, true);
    }
}