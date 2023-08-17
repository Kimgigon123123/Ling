package com.example.ling.date;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentDateBinding;
import com.example.ling.date.dibs.DibsActivity;
import com.example.ling.date.festival.FestivalActivity;
import com.example.ling.date.festival.FestivalAdapter;
import com.example.ling.date.festival.FestivalItemAdapter;
import com.example.ling.date.restaurant.RestaurantActivity;
import com.example.ling.date.restaurant.RestaurantItemAdapter;
import com.example.ling.date.tour.TourActivity;
import com.example.ling.date.tour.TourItemAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DateFragment extends Fragment {

    private FragmentDateBinding binding;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDateBinding.inflate(inflater, container, false);

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

        binding.imgvMenu.setOnClickListener(v -> {
            binding.navigationDrawer.setVisibility(View.VISIBLE);
            // 왼쪽에서 나타나는 애니메이션 설정
            Animation slideIn = new TranslateAnimation(-binding.navigationDrawer.getWidth(), 0, 0, 0);
            slideIn.setDuration(300); // 애니메이션 지속 시간 (밀리초)
            binding.navigationDrawer.startAnimation(slideIn);
        });

        binding.tvTour.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TourActivity.class);
            startActivity(intent);
        });

        binding.tvRestaurant.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RestaurantActivity.class);
            startActivity(intent);
        });

        binding.tvFestival.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FestivalActivity.class);
            startActivity(intent);
        });

        binding.tvDibs.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DibsActivity.class);
            startActivity(intent);
        });

        binding.tvNavClose.setOnClickListener(v -> {
            // 왼쪽으로 사라지는 애니메이션 설정
            Animation slideOut = new TranslateAnimation(0, -binding.navigationDrawer.getWidth(), 0, 0);
            slideOut.setDuration(300); // 애니메이션 지속 시간 (밀리초)
            binding.navigationDrawer.startAnimation(slideOut);
            binding.navigationDrawer.setVisibility(View.GONE);
        });


        binding.imgvDibs.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DibsActivity.class);
            startActivity(intent);
        });

        tour5();
        binding.tvTmore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TourActivity.class);
            startActivity(intent);
        });

        restaurant5();
        binding.tvRmore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RestaurantActivity.class);
            startActivity(intent);
        });

        festival5();
        binding.tvFmore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FestivalActivity.class);
            startActivity(intent);
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

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }

    public void tour5() {
        CommonConn conn = new CommonConn(getContext(), "date_tour5");
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvTour.setAdapter(new TourItemAdapter(list, getContext()));
            binding.recvTour.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        });
    }

    public void restaurant5() {
        CommonConn conn = new CommonConn(getContext(), "date_restaurant5");
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvRestaurant.setAdapter(new RestaurantItemAdapter(getContext(), list));
            binding.recvRestaurant.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        });
    }

    public void festival5() {
        CommonConn conn = new CommonConn(getContext(), "date_festival5");
        conn.onExcute((isResult, data) -> {
            ArrayList<DateInfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<DateInfoVO>>(){}.getType());
            binding.recvFestival.setAdapter(new FestivalItemAdapter(list, getContext()));
            binding.recvFestival.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        });
    }

}