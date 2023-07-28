package com.example.ling.store;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentStoreCoBinding;
import com.example.ling.date.DibsAdapter;


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

        return binding.getRoot();
    }
}