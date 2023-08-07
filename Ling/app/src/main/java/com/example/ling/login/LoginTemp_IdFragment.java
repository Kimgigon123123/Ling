package com.example.ling.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentLoginTempIdBinding;


public class LoginTemp_IdFragment extends Fragment {

    FragmentLoginTempIdBinding binding;


   CommonVar commonVar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginTempIdBinding.inflate(inflater, container, false);
        commonVar = new CommonVar();

        binding.findUserId.setText("사용자 아이디:"+ commonVar.loginInfo.getId());

        binding.btnReturnLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });

        return binding.getRoot();
    }
}