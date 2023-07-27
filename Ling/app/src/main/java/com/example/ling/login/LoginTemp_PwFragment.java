package com.example.ling.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentLoginTempPwBinding;


public class LoginTemp_PwFragment extends Fragment {

    FragmentLoginTempPwBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginTempPwBinding.inflate(inflater, container, false);

        binding.btnReturnLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });

        return binding.getRoot();
    }
}