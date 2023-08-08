package com.example.ling.store;

import android.app.Activity;
import android.content.Intent;
import android.opengl.EGLExt;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.chat.ChatFragment;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentStoreCoBinding;
import com.example.ling.date.DateFragment;
import com.example.ling.home.HomeFragment;
import com.example.ling.store.basket.BasketActivity;
import com.example.ling.store.myinfo.StoreMyinfoActivity;
import com.example.ling.store.myinfo.ZZimActivity;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class StoreCoFragment extends Fragment {
    String[] items = {"최신","이름","인기","가격"};


    FragmentStoreCoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStoreCoBinding.inflate(inflater, container, false);
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