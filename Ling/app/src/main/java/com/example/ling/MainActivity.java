package com.example.ling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ling.board.BoardFragment;

import com.example.ling.databinding.ActivityMainBinding;
import com.example.ling.date.DateFragment;
import com.example.ling.chat.ChatFragment;
import com.example.ling.home.HomeFragment;
import com.example.ling.store.StoreCoFragment;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Window window ;
    ArrayList<MainMenuDTO> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        initItem();
        initUI();


    }



    //바텀 네비게이션 아이템 초기화 메소드 2023-08-16 kym
    public void initItem(){
        //                      붙일프래그먼트  ,        아이콘               ,   선택배경색상 ,    글씨  ,       뱃지보여줄꺼면 뱃지 그씨
        list.add(new MainMenuDTO(new DateFragment() , R.drawable.btm_menu_1 ,"#FF8F1B" , "여행지" ,  "여행지 선택"  ));
        list.add(new MainMenuDTO(new ChatFragment() , R.drawable.btm_menu_2 ,"#567891" , "채팅" ,  null ));
        list.add(new MainMenuDTO(new HomeFragment() , R.drawable.btm_menu_3 ,"#123456" , "홈" ,  "홈 선택"  ));
        list.add(new MainMenuDTO(new BoardFragment() , R.drawable.btm_menu_4 ,"#5FB59C" , "게시판" ,  null  ));
        list.add(new MainMenuDTO(new StoreCoFragment() , R.drawable.btm_menu_5 ,"#847253" , "스토어" ,  "스토어 선택"  ));
    }

    //바텀 네비게이션 초기화 및 뷰페이저 초기화  ↗ 로직 끝나고 실행해야함  2023-08-16 kym
    private void initUI() {
       binding.pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
           @NonNull
           @Override
           public Fragment getItem(int i) {
               return list.get(i).fragment;
           }

           @Override
           public int getCount() {
               return list.size();
           }
       });

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
         NavigationTabBar.Model.Builder builder =   new NavigationTabBar.Model.Builder(
                    getResources().getDrawable(list.get(i).defaultIcon),
                    Color.parseColor(list.get(i).color))
                    .title(list.get(i).title);
            if(list.get(i).badgeTitle != null){
                builder.badgeTitle(list.get(i).badgeTitle);
            }
            models.add(builder.build());
        }

        binding.ntbHorizontal.setModels(models);
        binding.ntbHorizontal.setViewPager(binding.pager, 2);
        window.setStatusBarColor(Color.parseColor(list.get(2).color));
        binding.ntbHorizontal.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                window.setStatusBarColor(Color.parseColor(list.get(position).color));
                binding.ntbHorizontal.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        binding.ntbHorizontal.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < binding.ntbHorizontal.getModels().size(); i++) {
                    final NavigationTabBar.Model model = binding.ntbHorizontal.getModels().get(i);
                    binding.ntbHorizontal.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Log.d("뱃지", "run: " + model.getBadgeTitle());
                            if(model.getBadgeTitle()!=null && !model.getBadgeTitle().equals("0")) {

                                model.showBadge();
                            }
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

    public class MainMenuDTO {

    public MainMenuDTO(Fragment fragment, int defaultIcon, String color, String title, String badgeTitle) {
        this.fragment = fragment;
        this.defaultIcon = defaultIcon;
        this.color = color;
        this.title = title;
        this.badgeTitle = badgeTitle;
    }
      Fragment fragment;
        int defaultIcon , selectedIcon ;
        String  color , title , badgeTitle;

    }


}