package com.example.ling.join;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.databinding.FragmentJoinCompleteBinding;
import com.example.ling.login.LoginActivity;


public class JoinCompleteFragment extends Fragment {

    FragmentJoinCompleteBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJoinCompleteBinding.inflate(inflater, container, false);
        binding.btnGoHome.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });



        return binding.getRoot();
    }
}