package com.example.ling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ling.board.BoardFragment;

import com.example.ling.databinding.ActivityMainBinding;
import com.example.ling.date.DateFragment;
import com.example.ling.chat.ChatFragment;
import com.example.ling.home.HomeFragment;
import com.example.ling.store.ChargeVO;
import com.example.ling.store.CompleteDialog;
import com.example.ling.store.StoreCoFragment;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Fragment fragment;
    BoardFragment boardFragment = new BoardFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        initUI();

    }

    private void initUI() {
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
//        viewPager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return 5;
//            }
//
//            @Override
//            public boolean isViewFromObject(final View view, final Object object) {
//                return view.equals(object);
//            }
//
//            @Override
//            public void destroyItem(final View container, final int position, final Object object) {
//                ((ViewPager) container).removeView((View) object);
//            }
//
//            @Override
//            public Object instantiateItem(final ViewGroup container, final int position) {
//                final View view = LayoutInflater.from(
//                        getBaseContext()).inflate(R.layout.item_vp, null, false);
//
//                final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
//                txtPage.setText(String.format("Page #%d", position));
//
//                container.addView(view);
//                return view;
//            }
//        });

       // final String[] colors = getResources().getStringArray(R.array.default_preview);
        final String[] colors = { "#123456" , "#654321" , "#112233" , "#445566" , "#776655" };
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.travel),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.travel))
                        .title("Heart")
                        .badgeTitle("NTB")

                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_before),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Cup")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_before),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_before))
                        .title("Diploma")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_before),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Flag")
                        .badgeTitle("icon")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_before),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_before))
                        .title("Medal")
                        .badgeTitle("777")
                        .build()
        );

        navigationTabBar.setModels(models);
     //   navigationTabBar.setViewPager(viewPager, 2);
//        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(final int position) {
//                navigationTabBar.getModels().get(position).hideBadge();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(final int state) {
//
//            }
//        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
            manager.beginTransaction().replace(R.id.container, fragment).commit();

            return true;
        });

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
//        binding.navigation.setSelectedItemId(R.id.tab_home);
//        binding.navigation.setBackground(null);
//
////        binding.mainFab.setOnClickListener(view -> {
////            Intent intent = new Intent(MainActivity.this, CalendarActivity.class );
////            startActivity(intent);
////        });
//
//        binding.navigation.setItemIconTintList(null);
//        binding.navigation.setOnItemSelectedListener(item -> {
//
//
//            if(item.getItemId() == R.id.tab_travel){
//                fragment = new DateFragment();
//
//            }else if(item.getItemId() == R.id.tab_chat){
//                fragment = new ChatFragment();
//
//            }else if(item.getItemId() == R.id.tab_home){
//
//                fragment = new HomeFragment();
//
//            }else if(item.getItemId() == R.id.tab_store){
//
//                fragment = new StoreCoFragment();
//
//            }else if(item.getItemId() == R.id.tab_board){
//                fragment = boardFragment;
//
//            }
//            manager.beginTransaction().replace(R.id.container, fragment).commit();
//
//            return true;
//        });
//
//        //store 반품처리
//        Intent intent = getIntent();
//        String str = intent.getStringExtra("return");
//        if(str !=null && str.equals("return")){
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,new StoreCoFragment()).commit();
//        }
//
//
//    }
//
//    int backPressed = 0;
//    public void changeFragment(int backPressed, Fragment fragment){
//        this.backPressed = backPressed;
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
//    }
//    private long backKeyPressedTime = 0;
//    @Override
//    public void onBackPressed() {
//        // 기존의 뒤로가기 버튼의 기능 제거
//        // super.onBackPressed();
//
//        // 2000 milliseconds = 2 seconds
//        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
//            backKeyPressedTime = System.currentTimeMillis();
//            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
//            changeFragment(0 , new HomeFragment() );
//            return;
//        }
//
//        // 2초 이내에 뒤로가기 버튼을 한번 더 클릭시 finish()(앱 종료)
//        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
//            //finish();
//            finishAffinity();
//            System.runFinalization();
//            System.exit(0);
//        }
//
//
//    }

//    @Override
//    public void onBackPressed() {
//        if(backPressed==1){
//            changeFragment(0 , boardFragment );
//        }else {
//            finish();
//        }
//    }


//
//    getResources().getDrawable(R.drawable.ic_before),
//                        Color.parseColor(colors[4]))
//            .selectedIcon(getResources().getDrawable(R.drawable.ic_before))
//            .title("Medal")
//                        .badgeTitle("777")
//                        .build()
    public class MainMenuDTO {
        Fragment fragment;
    }


}