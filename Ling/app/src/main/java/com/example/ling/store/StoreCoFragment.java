package com.example.ling.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentStoreCoBinding;
import com.example.ling.store.basket.BasketActivity;
import com.example.ling.store.myinfo.StoreMyinfoActivity;
import com.example.ling.store.myinfo.ZZimActivity;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class StoreCoFragment extends Fragment {


    FragmentStoreCoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStoreCoBinding.inflate(inflater, container, false);
        select();
//        binding.recvStoreCo.setAdapter(new StoreCoAdater(getContext()));
//        binding.recvStoreCo.setLayoutManager(new GridLayoutManager(getContext(), 3));


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
        CommonConn conn = new CommonConn(getContext(), "test");
        conn.onExcute((isResult, data) -> {
            Log.d("결과값", "select: " + data);
            ArrayList<StoreCOVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreCOVO>>() {
            }.getType());
            Log.d("리스트 사이즈", "select: " + list.size());

            binding.recvStoreCo.setAdapter(new StoreCoAdater(list, getContext()));
            binding.recvStoreCo.setLayoutManager(new GridLayoutManager(getContext(),3));

        });

    }

}