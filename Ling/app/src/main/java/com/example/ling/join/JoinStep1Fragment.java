package com.example.ling.join;

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
import com.example.ling.databinding.FragmentJoinProfileBinding;
import com.example.ling.databinding.FragmentJoinStep1Binding;
import com.example.ling.login.Ling_MemberVO;
import com.example.ling.login.LoginActivity;
import com.example.ling.login.PreferenceManager;
import com.google.gson.Gson;

public class JoinStep1Fragment extends Fragment {

    FragmentJoinStep1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJoinStep1Binding.inflate(inflater, container, false);

        binding.checkId.setOnClickListener(v->{
            if(binding.joinId.getText().toString().length()<1){
                Toast.makeText(getActivity(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            CommonConn conn = new CommonConn(getActivity(), "useridCheck");
            conn.addParamMap("id", binding.joinId.getText().toString());
            conn.onExcute((isResult, data) -> {
                if(isResult){
                    CommonVar.loginInfo = new Gson().fromJson(data, Ling_MemberVO.class);

                    if(CommonVar.loginInfo != null){
                        Toast.makeText(getActivity(), "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        binding.joinId.setText("");
                    }else{
                        Toast.makeText(getActivity(), "사용가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        binding.checkId.setText("확인 완료");
                    }
                }
            });
        });

        binding.btnNext.setOnClickListener(v->{
            if(binding.joinId.getText().toString().length()<1
                    || binding.joinPw.getText().toString().length()<1
                    || binding.checkPw.getText().toString().length()<1){
                Toast.makeText(getActivity(), "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (binding.checkId.getText().equals("확인 완료")&&binding.joinPw.getText().toString().equals(binding.checkPw.getText().toString())) {

                CommonVar.loginInfo = new Ling_MemberVO();
               CommonVar.loginInfo.setId(binding.joinId.getText().toString());
               CommonVar.loginInfo.setPw(binding.checkPw.getText().toString());
                ((JoinActivity) getActivity()).changeTab(1);

            }else{
                Toast.makeText(getActivity(), "아이디 중복확인 혹인 비밀번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
            }

//            CommonConn conn = new CommonConn(getActivity(), "register");
//            conn.addParamMap("id", binding.joinId.getText().toString());
//            conn.addParamMap("pw", binding.joinPw.getText().toString());
////            conn.addParamMap("checkpw", binding.checkPw.getText().toString());
//            conn.onExcute((isResult, data) -> {
//                if(isResult){
//                    CommonVar.loginInfo = new Gson().fromJson(data, Ling_MemberVO.class);
//
//                    if (binding.joinPw.getText().toString().equals(binding.checkPw.getText().toString())) {
//
//                    }else{
//                        Toast.makeText(getActivity(), "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show();
//                        binding.joinPw.setText("");
//                        binding.checkPw.setText("");
//                        return;
//                    }
//
//                    if(CommonVar.loginInfo != null && binding.checkId.getText().equals("확인 완료")){
//                        ((JoinActivity) getActivity()).changeTab(1);
//                    }else{
//                       Toast.makeText(getActivity(), "아이디 중복확인 혹인 비밀번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });


        });


        binding.btnTextLogin.setOnClickListener(v->{

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        });

        return binding.getRoot();
    }
}
