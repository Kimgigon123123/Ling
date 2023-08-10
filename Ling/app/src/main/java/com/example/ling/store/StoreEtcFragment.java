package com.example.ling.store;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentStoreDrBinding;
import com.example.ling.databinding.FragmentStoreEtcBinding;
import com.example.ling.store.basket.BasketActivity;
import com.example.ling.store.myinfo.StoreMyinfoActivity;
import com.example.ling.store.myinfo.ZZimActivity;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class StoreEtcFragment extends Fragment {

    String[] items = {"최신","이름","인기","가격"};

    FragmentStoreEtcBinding binding;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStoreEtcBinding.inflate(inflater, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        binding.spinner.setAdapter(adapter);


        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str = items[position];
                if(str.equals("최신")){
                    select();
                }else if(str.equals("이름")){
                    by_name();

                }else if(str.equals("인기")){
                    by_popular();
                }else if(str.equals("가격")){
                    by_price();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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

        binding.tvAll.setOnClickListener(v -> {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreCoFragment storeCoFragment = new StoreCoFragment();
            transaction.replace(R.id.container, storeCoFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();


        });

        binding.tvDr.setOnClickListener(v->{

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreDrFragment storeDrFragment = new StoreDrFragment(); // StoreDrFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.container, storeDrFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();


        });

        binding.tvRing.setOnClickListener(v->{

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreRiFragment storeRiFragment= new StoreRiFragment(); // StoreDrFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.container, storeRiFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();


        });



        binding.tvGift.setOnClickListener(v->{

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreGiFragment storeGiFragment= new StoreGiFragment(); // StoreDrFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.container, storeGiFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();


        });

        binding.tvEtc.setOnClickListener(v->{

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            StoreEtcFragment storeEtcFragment= new StoreEtcFragment(); // StoreDrFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.container, storeEtcFragment);

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();
        });



        binding.imgvMyinfo.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StoreMyinfoActivity.class);
            getActivity().startActivity(intent);
        });

        binding.imgvZzim.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ZZimActivity.class);
            getActivity().startActivity(intent);
        });

        binding.imgvBasket.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), BasketActivity.class);
            getActivity().startActivity(intent);
        });






        return binding.getRoot();
    }








    public void select() {
        CommonConn conn = new CommonConn(getContext(), "store_by_recent");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreCOVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreCOVO>>() {
            }.getType());


            binding.recvStoreCo.setAdapter(new StoreCoAdater(list, getContext()));
            binding.recvStoreCo.setLayoutManager(new GridLayoutManager(getContext(),3));

        });

    }


    public void by_name() {
        CommonConn conn = new CommonConn(getContext(), "store_by_name");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreCOVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreCOVO>>() {
            }.getType());


            binding.recvStoreCo.setAdapter(new StoreCoAdater(list, getContext()));
            binding.recvStoreCo.setLayoutManager(new GridLayoutManager(getContext(),3));

        });

    }


    public void by_popular() {
        CommonConn conn = new CommonConn(getContext(), "store_by_popular");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreCOVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreCOVO>>() {
            }.getType());


            binding.recvStoreCo.setAdapter(new StoreCoAdater(list, getContext()));
            binding.recvStoreCo.setLayoutManager(new GridLayoutManager(getContext(),3));

        });

    }


    public void by_price() {
        CommonConn conn = new CommonConn(getContext(), "store_by_price");
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreCOVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreCOVO>>() {
            }.getType());


            binding.recvStoreCo.setAdapter(new StoreCoAdater(list, getContext()));
            binding.recvStoreCo.setLayoutManager(new GridLayoutManager(getContext(),3));

        });

    }

}