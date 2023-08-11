package com.example.ling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.board.BoardFragment;

import com.example.ling.databinding.ActivityMainBinding;
import com.example.ling.date.DateFragment;
import com.example.ling.chat.ChatFragment;
import com.example.ling.home.HomeFragment;
import com.example.ling.store.ChargeVO;
import com.example.ling.store.CompleteDialog;
import com.example.ling.store.StoreCoFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Fragment fragment;
    BoardFragment boardFragment = new BoardFragment();

    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        binding.navigation.setSelectedItemId(R.id.tab_home);
        binding.navigation.setBackground(null);

//        binding.mainFab.setOnClickListener(view -> {
//            Intent intent = new Intent(MainActivity.this, CalendarActivity.class );
//            startActivity(intent);
//        });

        binding.navigation.setItemIconTintList(null);
        binding.navigation.setOnItemSelectedListener(item -> {


            if(item.getItemId() == R.id.tab_travel){
                fragment = new DateFragment();

            }else if(item.getItemId() == R.id.tab_chat){
                fragment = new ChatFragment();

            }else if(item.getItemId() == R.id.tab_home){

                fragment = new HomeFragment();

            }else if(item.getItemId() == R.id.tab_store){

                fragment = new StoreCoFragment();

            }else if(item.getItemId() == R.id.tab_board){
                fragment = boardFragment;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
//        navigationTabBar.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
//                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
//                    navigationTabBar.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            model.showBadge();
//                        }
//                    }, i * 100);
//                }
//            }
//            manager.beginTransaction().replace(R.id.container, fragment).commit();
//
//            return true;
//        });
=======
=======
>>>>>>> parent of 6625ac9 (Merge remote-tracking branch 'origin/kym')
=======
>>>>>>> parent of 6625ac9 (Merge remote-tracking branch 'origin/kym')
=======
>>>>>>> parent of 6625ac9 (Merge remote-tracking branch 'origin/kym')
            }
            manager.beginTransaction().replace(R.id.container, fragment).commit();

            return true;
        });
>>>>>>> parent of 6625ac9 (Merge remote-tracking branch 'origin/kym')

        //store 반품처리
        Intent intent = getIntent();
        String str = intent.getStringExtra("return");
        if(str !=null && str.equals("return")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new StoreCoFragment()).commit();
        }

        //basket에서 구매
        if(ChargeVO.isBuy==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new StoreCoFragment()).commit();
            Dialog dialog = new CompleteDialog(this,"BuyComplete");
            dialog.show();
            ChargeVO.isBuy=false;
        }


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