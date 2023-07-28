package com.example.ling.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentBoardBinding;
import com.example.ling.databinding.FragmentBoardMainBinding;


public class BoardMainFragment extends Fragment {
    FragmentBoardMainBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardMainBinding.inflate(getLayoutInflater() , container , false);
        BoardFragment fragment = (BoardFragment) getParentFragment();
        binding.tvNoticeMore.setOnClickListener(v->{
            fragment.changeFragment(0);
        });
        binding.tvFreeMore.setOnClickListener(v->{
            fragment.changeFragment(2);
        });


        return binding.getRoot();
    }
}