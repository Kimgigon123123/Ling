package com.example.ling.store.myinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentBuylistBinding;
import com.example.ling.databinding.FragmentReturnBinding;
import com.example.ling.store.ReturnAdapter;


public class ReturnFragment extends Fragment {

    FragmentReturnBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReturnBinding.inflate(inflater,container,false);
        binding.recvReturn.setAdapter(new ReturnAdapter(getContext()));
        binding.recvReturn.setLayoutManager(new GridLayoutManager(getContext(),3));
        return binding.getRoot();
    }
}