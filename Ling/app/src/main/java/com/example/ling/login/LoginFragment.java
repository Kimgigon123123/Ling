package com.example.ling.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentLoginBinding;
import com.example.ling.join.JoinActivity;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.btnLogin.setOnClickListener(v->{
            ((LoginActivity) getActivity()).find_changeTab(6);
        });

        binding.btnTextFind.setOnClickListener(v->{
            ((LoginActivity) getActivity()).find_changeTab(1);
        });

        binding.btnTextJoin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), JoinActivity.class);
            startActivity(intent);

        });

        return binding.getRoot();
    }
}