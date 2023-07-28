package com.example.ling.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.databinding.FragmentStoreCoBinding;
import com.example.ling.store.basket.BasketActivity;
import com.example.ling.store.myinfo.StoreMyinfoActivity;
import com.example.ling.store.myinfo.ZZimActivity;


public class StoreCoFragment extends Fragment {


    FragmentStoreCoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentStoreCoBinding.inflate(inflater,container,false);

        binding.recvStoreCo.setAdapter(new StoreCoAdater(getContext()));
        binding.recvStoreCo.setLayoutManager(new GridLayoutManager(getContext(), 3));


        binding.imgvMyinfo.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StoreMyinfoActivity.class);
            getActivity().startActivity(intent);
        });

        binding.imgvZzim.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), ZZimActivity.class);
            getActivity().startActivity(intent);
        });

        binding.imgvBasket.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), BasketActivity.class);
            getActivity().startActivity(intent);
        });

        return binding.getRoot();
    }
}