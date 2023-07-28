package com.example.ling.store.myinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.databinding.FragmentZZimBinding;


public class ZZimFragment extends Fragment {

    FragmentZZimBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentZZimBinding.inflate(inflater,container,false);
        binding.recvZzim.setAdapter(new ZZimMoreAdapter(getContext()));
        binding.recvZzim.setLayoutManager(new GridLayoutManager(getContext(),3));
        return binding.getRoot();
    }
}