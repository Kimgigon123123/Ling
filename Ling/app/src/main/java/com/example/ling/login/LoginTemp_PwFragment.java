package com.example.ling.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentLoginFindPwBinding;
import com.example.ling.databinding.FragmentLoginTempIdBinding;
import com.example.ling.databinding.FragmentLoginTempPwBinding;
import com.example.ling.databinding.FragmentLoginfindIdBinding;


public class LoginTemp_PwFragment extends Fragment {

    FragmentLoginTempPwBinding binding;
    FragmentLoginTempIdBinding bindingTi;
    CommonVar commonVar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (((LoginActivity) getActivity()).isFindCheck) {
            bindingTi = FragmentLoginTempIdBinding.inflate(inflater, container, false);
            idTUIMethod(inflater, container);
            return bindingTi.getRoot();// = FragmentLoginFindPwBinding.inflate(inflater, container, false);
        } else {
            binding = FragmentLoginTempPwBinding.inflate(inflater, container, false);
            pwTUIMethod(inflater, container);
            return binding.getRoot();   //binding = FragmentLoginfindIdBinding.inflate(inflater, container, false);
        }
    }
        public void pwTUIMethod(LayoutInflater inflater, ViewGroup container){
            binding.btnReturnLogin.setOnClickListener(v->{
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            });

        }

        public void idTUIMethod(LayoutInflater inflater , ViewGroup container){
            bindingTi = FragmentLoginTempIdBinding.inflate(inflater, container, false);
            commonVar = new CommonVar();
            if(commonVar.loginInfo != null){
                bindingTi.findUserId.setText("사용자 아이디:"+ commonVar.loginInfo.getId());
            }

            bindingTi.btnReturnLogin.setOnClickListener(v->{
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            });
        }




}