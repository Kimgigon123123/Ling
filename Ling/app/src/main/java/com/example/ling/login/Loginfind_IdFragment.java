package com.example.ling.login;

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
import com.example.ling.databinding.FragmentLoginFindPwBinding;
import com.example.ling.databinding.FragmentLoginfindIdBinding;
import com.google.gson.Gson;


public class Loginfind_IdFragment extends Fragment {


    FragmentLoginfindIdBinding binding;
    FragmentLoginFindPwBinding bindingPw;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(((LoginActivity) getActivity()).isFindCheck){
            binding = FragmentLoginfindIdBinding.inflate(inflater, container, false);
            idUIMethod(inflater , container);
            return binding.getRoot();   //binding = FragmentLoginfindIdBinding.inflate(inflater, container, false);
        }else{
            bindingPw = FragmentLoginFindPwBinding.inflate(inflater, container, false);
            pwUIMethod(inflater , container);
            return bindingPw.getRoot();// = FragmentLoginFindPwBinding.inflate(inflater, container, false);
        }


    }
    public void idUIMethod(LayoutInflater inflater , ViewGroup container){
        binding = FragmentLoginfindIdBinding.inflate(inflater, container, false);
        binding.btnNext.setOnClickListener(v->{

            if(binding.userName.getText().toString().length()<1
                    || binding.userPhone.getText().toString().length()<1){
                Toast.makeText(getActivity(), "이름과 전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            CommonConn conn = new CommonConn(getActivity(), "findid");
            conn.addParamMap("name", binding.userName.getText().toString());
            conn.addParamMap("phone", binding.userPhone.getText().toString());
            conn.onExcute((isResult, data) -> {
                if(isResult){
                    CommonVar.loginInfo = new Gson().fromJson(data, Ling_MemberVO.class);
                    if(CommonVar.loginInfo==null){
                        Toast.makeText(getActivity(), "사용자 이름 또는 전화번호 확인", Toast.LENGTH_SHORT).show();
                    }else{

                        ((LoginActivity) getActivity()).find_changeTab(3);
                    }
                }
            });

        });
        binding.btnTextLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });

    }

    public void pwUIMethod(LayoutInflater inflater , ViewGroup container){
        bindingPw = FragmentLoginFindPwBinding.inflate(inflater, container,false);
        bindingPw.btnNext.setOnClickListener(v->{
            if(bindingPw.userId.getText().toString().length()<1
                    || bindingPw.userEmail.getText().toString().length()<1){
                Toast.makeText(getActivity(), "아이디와 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            CommonConn conn = new CommonConn(getActivity(), "resetPassword");
            conn.addParamMap("id", bindingPw.userId.getText().toString());
            conn.addParamMap("email", bindingPw.userEmail.getText().toString());
            conn.onExcute((isResult, data) -> {
                if(isResult){
                    CommonVar.loginInfo = new Gson().fromJson(data, Ling_MemberVO.class);
                    if(CommonVar.loginInfo==null){
                        Toast.makeText(getActivity(), "사용자 아이디 또는 이메일 확인", Toast.LENGTH_SHORT).show();
                    }else{

                        ((LoginActivity) getActivity()).find_changeTab(3);
                    }
                }
            });

        });
        bindingPw.btnTextLogin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });
    }


}