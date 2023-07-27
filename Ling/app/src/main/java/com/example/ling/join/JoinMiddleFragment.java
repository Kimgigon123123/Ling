package com.example.ling.join;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentJoinMiddleBinding;


public class JoinMiddleFragment extends Fragment {

    FragmentJoinMiddleBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJoinMiddleBinding.inflate(inflater, container, false);
        binding.btnNext.setOnClickListener(v->{
            ((JoinActivity) getActivity()).changeTab(3);
        });
        return binding.getRoot();
    }
}