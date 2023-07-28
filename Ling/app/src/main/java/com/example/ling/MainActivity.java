package com.example.ling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ling.board.BoardFragment;

import com.example.ling.databinding.ActivityMainBinding;
import com.example.ling.date.DateFragment;
import com.example.ling.home.ChatFragment;
import com.example.ling.home.HomeFragment;
import com.example.ling.store.StorePurchaseActivity;

import nl.dionsegijn.konfetti.KonfettiView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Fragment fragment;
    BoardFragment boardFragment = new BoardFragment();
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

                Intent intent = new Intent(MainActivity.this, StorePurchaseActivity.class);
                startActivity(intent);

            }else if(item.getItemId() == R.id.tab_board){
                fragment = boardFragment;

            }
            manager.beginTransaction().replace(R.id.container, fragment).commit();

            return true;
        });


    }

    int backPressed = 0;
    public void changeFragment(int backPressed, Fragment fragment){
        this.backPressed = backPressed;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
    private long backKeyPressedTime = 0;
    @Override
    public void onBackPressed() {
        // 기존의 뒤로가기 버튼의 기능 제거
        // super.onBackPressed();

        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            changeFragment(0 , new HomeFragment() );
            return;
        }

        // 2초 이내에 뒤로가기 버튼을 한번 더 클릭시 finish()(앱 종료)
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            //finish();
            finishAffinity();
            System.runFinalization();
            System.exit(0);
        }
    }

//    @Override
//    public void onBackPressed() {
//        if(backPressed==1){
//            changeFragment(0 , boardFragment );
//        }else {
//            finish();
//        }
//    }

}