package com.example.ling.store.myinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentZZimBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class ZZimFragment extends Fragment {

    FragmentZZimBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentZZimBinding.inflate(inflater,container,false);
        select();
//        binding.recvZzim.setAdapter(new ZZimMoreAdapter(getContext()));
//        binding.recvZzim.setLayoutManager(new GridLayoutManager(getContext(),3));
        return binding.getRoot();
    }

    public void select(){
        CommonConn conn = new CommonConn(getContext(), "store_list_zzim");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreZzimListVO> zzimlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreZzimListVO>>() {
            }.getType());
            binding.recvZzim.setAdapter(new ZZimAdapter(zzimlist,getContext()));
            binding.recvZzim.setLayoutManager(new GridLayoutManager(getContext(),4));

            if(zzimlist.size()==0){
                binding.tvEmpty.setVisibility(View.VISIBLE);
            }

        });
    }



}


