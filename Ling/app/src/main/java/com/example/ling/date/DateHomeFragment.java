package com.example.ling.date;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentDateBinding;
import com.example.ling.date.dibs.DibsActivity;
import com.example.ling.date.list.DateInfoVO;
import com.example.ling.date.list.DateListActivity;
import com.example.ling.date.slider.SliderAdapter;
import com.example.ling.date.slider.SliderVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DateHomeFragment extends Fragment {

    private FragmentDateBinding binding;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDateBinding.inflate(inflater, container, false);

        showSlide();//슬라이드 동작.

        drawerLayoutEvent();//DrawerLayout이벤트.( 삭제 예정 )


        selectCountList("TO",5,binding.recvTour);
        selectCountList("RE",5,binding.recvRestaurant);
        selectCountList("FE",5,binding.recvFestival);


//        binding.tvTour.setOnClickListener(v -> {
//            intentActivity("TO");
//        });
//
//        binding.tvRestaurant.setOnClickListener(v -> {
//            intentActivity("RE");
//        });
//
//        binding.tvFestival.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), DateListActivity.class);
//            startActivity(intent);
//        });
//
//        binding.tvDibs.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), DibsActivity.class);
//            startActivity(intent);
//        });
//


        binding.btnDibs.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DibsActivity.class);

            startActivity(intent);
        });


        binding.tvTmore.setOnClickListener(v -> {
            intentActivity("TO");
        });


        binding.tvRmore.setOnClickListener(v -> {
            intentActivity("RE");
        });


        binding.tvFmore.setOnClickListener(v -> {
            intentActivity("FE");
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            binding.vpSlider.setCurrentItem(binding.vpSlider.getCurrentItem() + 1);
        }
    };
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }



    //2023.08.23 KYM 수정예정
    void drawerLayoutEvent(){

        binding.imgvMenu.setOnClickListener(v -> {
            binding.navigationDrawer.setVisibility(View.VISIBLE);
            // 왼쪽에서 나타나는 애니메이션 설정
            Animation slideIn = new TranslateAnimation(-binding.navigationDrawer.getWidth(), 0, 0, 0);
            slideIn.setDuration(300); // 애니메이션 지속 시간 (밀리초)
            binding.navigationDrawer.startAnimation(slideIn);
        });

        binding.tvNavClose.setOnClickListener(v -> {
            // 왼쪽으로 사라지는 애니메이션 설정
            Animation slideOut = new TranslateAnimation(0, -binding.navigationDrawer.getWidth(), 0, 0);
            slideOut.setDuration(300); // 애니메이션 지속 시간 (밀리초)
            binding.navigationDrawer.startAnimation(slideOut);
            binding.navigationDrawer.setVisibility(View.GONE);
        });
    }


    //2023.08.23 KYM 코드 햇갈려서 메소드로 분리만함.
    void showSlide(){
        ArrayList<SliderVO> list = new ArrayList<>();
        list.add(new SliderVO(R.drawable.sdimg1, "전국 여행"));
        list.add(new SliderVO(R.drawable.sdimg2, "전국 맛집"));
        list.add(new SliderVO(R.drawable.sdimg3, "전국 축제"));

        binding.vpSlider.setAdapter(new SliderAdapter(getContext(), binding.vpSlider, list));

        binding.vpSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }




    public void intentActivity(String category){
        Intent intent = new Intent(getActivity(), DateListActivity.class);
        intent.putExtra("category" , category);
        startActivity(intent);
    }
    //2023-08-23 KYM 수정
    public void selectCountList(String category , int count , RecyclerView recv) {
        CommonConn conn = new CommonConn(getContext(), "date_count_list");
        conn.addParamMap("category" , category);
        conn.addParamMap("count" , count);
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            recv.setAdapter(new DateMainItemAdapter(list, getContext()));
            recv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        });
    }


}