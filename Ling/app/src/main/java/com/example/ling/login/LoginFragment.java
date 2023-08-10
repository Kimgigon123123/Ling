package com.example.ling.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.Static;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentLoginBinding;
import com.example.ling.join.JoinActivity;
import com.google.gson.Gson;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        mContext = getActivity(); // 이거 필수!





        binding.btnLogin.setOnClickListener(v->{
            binding.chkLogin.setChecked(false);
            PreferenceManager.setString(mContext, "id", binding.edtId.getText().toString()); //id라는 키값으로 저장
            PreferenceManager.setString(mContext, "pw", binding.edtPw.getText().toString()); //pw라는 키값으로 저장
            String checkId = PreferenceManager.getString(mContext, "id");
            String checkPw = PreferenceManager.getString(mContext, "pw");
            //아이디와 암호가 비어있는 경우를 체크
            if (TextUtils.isEmpty(checkId) || TextUtils.isEmpty(checkPw)){
                //아이디나 암호 둘 중 하나가 비어있으면 토스트메시지를 띄운다
                Toast.makeText(getActivity(), "아이디/암호를 입력해주세요",
                        Toast.LENGTH_SHORT).show();
            }else { //둘 다 충족하면 다음 동작을 구현해놓음

                if (binding.edtId.getText().toString().length() < 1
                        || binding.edtPw.getText().toString().length() < 1) {
                    Toast.makeText(getActivity(), "아이디또는 비번입력", Toast.LENGTH_SHORT).show();
                    return;
                }
                CommonConn conn = new CommonConn(getActivity(), "login");
                conn.addParamMap("id", binding.edtId.getText().toString());
                conn.addParamMap("pw", binding.edtPw.getText().toString());
                conn.onExcute((isResult, data) -> {
                    if (isResult) {
                        CommonVar.loginInfo = new Gson().fromJson(data, Ling_MemberVO.class);


                        if (CommonVar.loginInfo == null) {
                            Toast.makeText(getActivity(), "아이디 비번 확인", Toast.LENGTH_SHORT).show();
                        } else {
                            binding.chkLogin.setChecked(true);
                            ((LoginActivity) getActivity()).find_changeTab(6);

                            //로그인하면 로그인한 id의 회원 정보를 store id 회원 정보로 넘기는 처리 (김기곤)





                        }
                    }
                });
            }


        });

        //로그인 기억하기 체크박스 유무에 따른 동작 구현
        binding.chkLogin.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) { // 체크박스 체크 되어 있으면
                    //editText에서 아이디와 암호 가져와 PreferenceManager에 저장한다.
                    PreferenceManager.setString(mContext, "id", binding.edtId.getText().toString()); //id 키값으로 저장
                    PreferenceManager.setString(mContext, "pw", binding.edtPw.getText().toString()); //pw 키값으로 저장
                    PreferenceManager.setBoolean(mContext, "check", binding.chkLogin.isChecked()); //현재 체크박스 상태 값 저장
                } else { //체크박스가 해제되어있으면
                    PreferenceManager.setBoolean(mContext, "check", binding.chkLogin.isChecked()); //현재 체크박스 상태 값 저장
                    PreferenceManager.clear(mContext); //로그인 정보를 모두 날림
                }
            }
        }) ;


        binding.btnTextFind.setOnClickListener(v->{
            ((LoginActivity) getActivity()).find_changeTab(1);
        });

        binding.btnTextJoin.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), JoinActivity.class);
            startActivity(intent);

        });


        binding.chkLogin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            PreferenceManager.setBoolean(mContext, "check", isChecked);
            //PreferenceManager.getBoolean(mContext,"check");
        });

        boolean boo = PreferenceManager.getBoolean(mContext,"check"); //로그인 정보 기억하기 체크 유무 확인
        if(boo){ // 체크가 되어있다면 아래 코드를 수행
            //저장된 아이디와 암호를 가져와 셋팅한다.
            binding.edtId.setText(PreferenceManager.getString(mContext, "id"));
            binding.edtPw.setText(PreferenceManager.getString(mContext, "pw"));
            binding.chkLogin.setChecked(true); //체크박스는 여전히 체크 표시 하도록 셋팅
            //binding.btnLogin.performClick();
        }


        return binding.getRoot();
    }
}