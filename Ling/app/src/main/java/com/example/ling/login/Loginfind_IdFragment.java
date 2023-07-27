package com.example.ling.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentLoginfindIdBinding;


public class Loginfind_IdFragment extends Fragment {

    FragmentLoginfindIdBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginfindIdBinding.inflate(inflater, container, false);
        binding.btnNext.setOnClickListener(v->{
            ((LoginActivity) getActivity()).find_changeTab(3);
        });

        binding.btnTextLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });
        return binding.getRoot();
    }
}