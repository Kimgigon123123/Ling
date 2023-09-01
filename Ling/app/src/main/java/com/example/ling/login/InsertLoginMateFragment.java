package com.example.ling.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentInsertLoginMateBinding;
import com.example.ling.databinding.FragmentInsertMateBinding;
import com.example.ling.join.JoinActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;


public class InsertLoginMateFragment extends Fragment {

    FragmentInsertLoginMateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInsertLoginMateBinding.inflate(inflater, container, false);
        binding.btnNext.setOnClickListener(v->{
            if((binding.insertId.getText().toString().length()<1)){
                Toast.makeText(getActivity(), "빈칸없이 입력해주세요.", Toast.LENGTH_SHORT).show();
            }else {
                CommonConn conn = new CommonConn(getActivity(), "member.matching");
                HashMap<String, Object> map = new HashMap<String, Object>();
                if (CommonVar.loginInfo.getGender().equals("남")) {
                    map.put("mid", CommonVar.loginInfo.getId());
                    map.put("fid", binding.insertId.getText().toString());
                } else {
                    map.put("fid", CommonVar.loginInfo.getId());
                    map.put("mid", binding.insertId.getText().toString());
                }
                map.put("gender", CommonVar.loginInfo.getGender());
                conn.addParamMap("dto", new Gson().toJson(map));
                conn.onExcute((isResult, data) -> {
                    if (isResult) {
                        CommonVar.idMap.put("mid",map.get("mid").toString());
                        CommonVar.idMap.put("fid", map.get("fid").toString());
                        HashMap<String, Object> receive = new Gson().fromJson(data,  new TypeToken<HashMap<String,Object>>(){}.getType());
                        if (receive.get("status").toString().equals("F")) {
                            ((LoginActivity) getActivity()).find_changeTab(6);
                        } else {
                            Toast.makeText(getActivity(), "아이디를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                            binding.insertId.setText("");
                        }

                    }
                });

            }
        });




        return binding.getRoot();
    }
}