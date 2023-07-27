package com.example.ling.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentLoginFindBinding;
import com.example.ling.join.JoinActivity;


public class LoginFindFragment extends Fragment {

    FragmentLoginFindBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentLoginFindBinding.inflate(inflater, container, false);
        binding.btnFindId.setOnClickListener(v->{
            ((LoginActivity) getActivity()).find_changeTab(2);
        });

        binding.btnFindPw.setOnClickListener(v->{
            ((LoginActivity) getActivity()).find_changeTab(4);
        });

        binding.btnTextLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });

        return binding.getRoot();
    }
}