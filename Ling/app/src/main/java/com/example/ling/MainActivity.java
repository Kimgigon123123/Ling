package com.example.ling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ling.board.BoardFragment;

import com.example.ling.databinding.ActivityMainBinding;
import com.example.ling.date.DateHomeFragment;
import com.example.ling.chat.ChatFragment;
import com.example.ling.home.HomeFragment;
import com.example.ling.store.ChargeVO;
import com.example.ling.store.CompleteDialog;
import com.example.ling.store.StoreCoFragment;
import com.example.ling.store.StoreMainFragment;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Window window ;
    private boolean doubleBackToExitPressedOnce = false;
    private Handler mHandler = new Handler();
    ArrayList<MainMenuDTO> list = new ArrayList<>();
    String couple_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        AnimationDrawable animDrawable = (AnimationDrawable) binding.layoutMain.getBackground();
        animDrawable.setEnterFadeDuration(10);
        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();




        initItem();
        initUI();

    }


    @Override
    protected void onStart() {
        super.onStart();


        //김기곤이 만듬 (스토어 장바구니 구매시 구매완료 다이얼로그 뜨겁게하기)
        if( ChargeVO.isBuy == true){
            Dialog dialog = new CompleteDialog(this,"BuyComplete");
            dialog.show();
            ChargeVO.isBuy=false;
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();


    }

    //바텀 네비게이션 아이템 초기화 메소드 2023-08-16 kym
    public void initItem(){
        list = new ArrayList<>();
        //                      붙일프래그먼트  ,        아이콘               ,   선택배경색상 ,    글씨  ,       뱃지보여줄꺼면 뱃지 그씨
        list.add(new MainMenuDTO(new DateHomeFragment() , R.drawable.btm_menu_1 ,"#F8E8EE" , "여행지" ,  "여행지 선택"  ));
        list.add(new MainMenuDTO(new ChatFragment() , R.drawable.btm_menu_2 ,"#FDCEDF" , "채팅" ,  null ));
        list.add(new MainMenuDTO(new HomeFragment() , R.drawable.btm_menu_3 ,"#1A000000" , "홈" ,  "홈 선택"  ));
        list.add(new MainMenuDTO(new BoardFragment() , R.drawable.btm_menu_4 ,"#F2BED1" , "게시판" ,  null  ));
        list.add(new MainMenuDTO(new StoreMainFragment() , R.drawable.btm_menu_5 ,"#F9F5F6" , "스토어" ,  "스토어 선택"  ));
    }

    //바텀 네비게이션 초기화 및 뷰페이저 초기화  ↗ 로직 끝나고 실행해야함  2023-08-16 kym
    private void initUI() {
       binding.pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
           @NonNull
           @Override
           public Fragment getItem(int i) {
               if(i == 0 ){
                   return  new DateHomeFragment();
               }else if (i == 1){
                   return  new ChatFragment();
               }else if (i == 2){
                   return  new HomeFragment();
               }else if (i == 3){
                   return  new BoardFragment();
               }else if (i == 4){
                   return  new StoreMainFragment();
               }else{
                   return new HomeFragment();
               }
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

        binding.pager.setOffscreenPageLimit(0);
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

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}