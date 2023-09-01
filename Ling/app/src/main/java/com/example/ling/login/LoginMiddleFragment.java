package com.example.ling.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.ling.databinding.FragmentJoinMiddleBinding;
import com.example.ling.databinding.FragmentLoginMiddleBinding;
import com.example.ling.join.JoinActivity;


public class LoginMiddleFragment extends Fragment {

    FragmentLoginMiddleBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginMiddleBinding.inflate(inflater, container, false);
        binding.btnNext.setOnClickListener(v->{

            ((LoginActivity) getActivity()).find_changeTab(5);
        });
        binding.btnReturnLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }
}