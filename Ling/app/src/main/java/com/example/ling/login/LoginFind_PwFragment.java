package com.example.ling.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentLoginFindPwBinding;
import com.google.gson.Gson;


public class LoginFind_PwFragment extends Fragment {

    FragmentLoginFindPwBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginFindPwBinding.inflate(inflater, container,false);
        binding.btnNext.setOnClickListener(v->{
            if(binding.userId.getText().toString().length()<1
                    || binding.userEmail.getText().toString().length()<1){
                Toast.makeText(getActivity(), "아이디와 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            CommonConn conn = new CommonConn(getActivity(), "resetPassword");
            conn.addParamMap("id", binding.userId.getText().toString());
            conn.addParamMap("email", binding.userEmail.getText().toString());
            conn.onExcute((isResult, data) -> {
                if(isResult){
                    CommonVar.loginInfo = new Gson().fromJson(data, Ling_MemberVO.class);
                    if(CommonVar.loginInfo==null){
                        Toast.makeText(getActivity(), "사용자 아이디 또는 이메일 확인", Toast.LENGTH_SHORT).show();
                    }else{

                        ((LoginActivity) getActivity()).find_changeTab(5);
                    }
                }
            });

        });
        binding.btnTextLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });

        return binding.getRoot();
    }
}