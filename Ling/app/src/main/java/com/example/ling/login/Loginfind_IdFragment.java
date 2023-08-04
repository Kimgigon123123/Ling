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
import com.example.ling.databinding.FragmentLoginfindIdBinding;
import com.google.gson.Gson;


public class Loginfind_IdFragment extends Fragment {

    FragmentLoginfindIdBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        return binding.getRoot();
    }
}