package com.example.ling.store.myinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentBuylistBinding;
import com.example.ling.databinding.FragmentZZimBinding;


public class BuylistFragment extends Fragment {




    FragmentBuylistBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBuylistBinding.inflate(inflater,container,false);
        binding.recvBuylist.setAdapter(new BuylistMoreAdapter(getContext()));
        binding.recvBuylist.setLayoutManager(new GridLayoutManager(getContext(),3));
        return binding.getRoot();
    }
}