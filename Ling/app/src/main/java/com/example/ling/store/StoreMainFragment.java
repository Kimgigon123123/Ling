package com.example.ling.store;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentDateBinding;
import com.example.ling.databinding.FragmentStoreMainBinding;
import com.example.ling.store.basket.BasketActivity;
import com.example.ling.store.myinfo.StoreMyinfoActivity;
import com.example.ling.store.myinfo.ZZimActivity;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class StoreMainFragment extends Fragment {

    FragmentStoreMainBinding binding;

     ImagePagerAdapter adapter;

     int currentPage = 0;
     final long delayTime = 3000; // 3초
    Handler handler = new Handler(Looper.getMainLooper());
     Runnable runnable = new Runnable() {
        public void run() {
            if (currentPage == adapter.getCount()) {
                currentPage = 0;
            }
            binding.viewPager.setCurrentItem(currentPage++, true);
            handler.postDelayed(this, delayTime);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoreMainBinding.inflate(inflater, container, false);

        adapter = new ImagePagerAdapter(getContext());
        binding.viewPager.setAdapter(adapter);

        select("popular");




        handler.postDelayed(runnable, delayTime);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

                // 숫자 업데이트
                binding.currentPageText.setText(String.valueOf(currentPage + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        binding.totalPagesText.setText(String.valueOf(adapter.getCount()));

        binding.imgvMenu.setOnClickListener(v -> {
            binding.fl.setVisibility(View.VISIBLE);
//            ll.setBackgroundColor(Color.BLACK);
            // 왼쪽에서 나타나는 애니메이션 설정
            Animation slideIn = new TranslateAnimation(-binding.fl.getWidth(), 0, 0, 0);
            slideIn.setDuration(300); // 애니메이션 지속 시간 (밀리초)
            binding.fl.startAnimation(slideIn);
        });

        binding.tvNavClose.setOnClickListener(v -> {
            // 왼쪽으로 사라지는 애니메이션 설정
            Animation slideOut = new TranslateAnimation(0, -binding.fl.getWidth(), 0, 0);
            slideOut.setDuration(300); // 애니메이션 지속 시간 (밀리초)
            binding.fl.startAnimation(slideOut);
            binding.fl.setVisibility(View.GONE);


        });

        binding.fl.setOnClickListener(v->{

        });

        binding.tvHome.setOnClickListener(v -> {

            StoreMainFragment newFragment = new StoreMainFragment();

            // 프래그먼트 트랜잭션 설정
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // 기존의 StoreMainFragment 대신 새로운 StoreMainFragment로 교체
            transaction.replace(R.id.store_home, newFragment);

            transaction.addToBackStack(null);
            transaction.commit();
        });



        binding.tvAll.setOnClickListener(v -> {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreCoFragment storeCoFragment = new StoreCoFragment();
            transaction.replace(R.id.store_home, storeCoFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();


        });

        binding.tvDr.setOnClickListener(v->{

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreDrFragment storeDrFragment = new StoreDrFragment(); // StoreDrFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.store_home, storeDrFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();


        });



        binding.tvRing.setOnClickListener(v->{

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreRiFragment storeRiFragment= new StoreRiFragment(); // StoreDrFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.store_home, storeRiFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();


        });



        binding.tvGift.setOnClickListener(v->{

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreGiFragment storeGiFragment= new StoreGiFragment(); // StoreDrFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.store_home, storeGiFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();


        });

        binding.tvEtc.setOnClickListener(v->{

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreEtcFragment storeEtcFragment= new StoreEtcFragment(); // StoreDrFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.store_home, storeEtcFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();
        });

        binding.btnMyinfo.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StoreMyinfoActivity.class);
            getActivity().startActivity(intent);
        });

        binding.btnZzim.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ZZimActivity.class);
            getActivity().startActivity(intent);
        });

        binding.btnBasket.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), BasketActivity.class);
            getActivity().startActivity(intent);
        });

        binding.tvMore.setOnClickListener(v->{
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreCoFragment storeCoFragment = new StoreCoFragment();
            transaction.replace(R.id.store_home, storeCoFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();
        });

        return binding.getRoot();
    }

    public void select(String order) {
        CommonConn conn = new CommonConn(getContext(), "storelist");
        conn.addParamMap("orderby"  , order);
        conn.addParamMap("id" , CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreCOVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreCOVO>>() {
            }.getType());


            binding.recvItem.setAdapter(new StoreCoAdater(list, getContext()));
            binding.recvItem.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        });

    }





}