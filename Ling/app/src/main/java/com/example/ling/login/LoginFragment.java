package com.example.ling.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentLoginBinding;
import com.example.ling.join.JoinActivity;
import com.google.gson.Gson;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.btnLogin.setOnClickListener(v->{
            if(binding.edtId.getText().toString().length()<1
                    || binding.edtPw.getText().toString().length()<1){
                Toast.makeText(getActivity(), "아이디또는 비번입력", Toast.LENGTH_SHORT).show();
                return;
            }
            CommonConn conn = new CommonConn(this, "login");
            conn.addParamMap("id", binding.edtId.getText().toString());
            conn.addParamMap("password", binding.edtPw.getText().toString());
            conn.onExcute((isResult, data) -> {
                if(isResult){
                    CommonVar.loginInfo = new Gson().fromJson(data, Ling_MemberVO.class);
                    if(CommonVar.loginInfo==null){
                        Toast.makeText(getActivity(), "아이디 비번 확인", Toast.LENGTH_SHORT).show();
                    }else{
                        ((LoginActivity) getActivity()).find_changeTab(6);
                    }
                }
            });

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