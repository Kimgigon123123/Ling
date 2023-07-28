package com.example.ling.join;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentJoinStep1Binding;
import com.example.ling.login.LoginActivity;

public class JoinStep1Fragment extends Fragment {

    FragmentJoinStep1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJoinStep1Binding.inflate(inflater, container, false);
        binding.btnNext.setOnClickListener(v->{
            ((JoinActivity) getActivity()).changeTab(1);
        });

        binding.btnTextLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });

        return binding.getRoot();
    }
}
