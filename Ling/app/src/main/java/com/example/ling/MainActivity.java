package com.example.ling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;

import com.example.ling.board.BoardFragment;

import com.example.ling.databinding.ActivityMainBinding;
import com.example.ling.date.DateFragment;
import com.example.ling.home.ChatFragment;
import com.example.ling.home.HomeFragment;
import com.example.ling.store.StorePurchaseActivity;
import com.example.ling.store.StorePurchaseFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        binding.navigation.setSelectedItemId(R.id.tab_home);
        binding.navigation.setBackground(null);

//        binding.mainFab.setOnClickListener(view -> {
//            Intent intent = new Intent(MainActivity.this, CalendarActivity.class );
//            startActivity(intent);
//        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        binding.navigation.setItemIconTintList(null);
        binding.navigation.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.tab_travel){
                fragment = new DateFragment();

            }else if(item.getItemId() == R.id.tab_chat){
                fragment = new ChatFragment();

            }else if(item.getItemId() == R.id.tab_home){

                fragment = new HomeFragment();

            }else if(item.getItemId() == R.id.tab_store){

                fragment = new StorePurchaseFragment();

            }else if(item.getItemId() == R.id.tab_board){
                fragment = new BoardFragment();

            }
            manager.beginTransaction().replace(R.id.container, fragment).commit();

            return true;
        });
    }
}