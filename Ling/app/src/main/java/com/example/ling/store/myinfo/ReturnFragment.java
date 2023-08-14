package com.example.ling.store.myinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentReturnBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class ReturnFragment extends Fragment {

    FragmentReturnBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReturnBinding.inflate(inflater,container,false);
        returnlist();
//        binding.recvReturn.setAdapter(new ReturnMoreAdapter(getContext()));
//        binding.recvReturn.setLayoutManager(new GridLayoutManager(getContext(),3));
        return binding.getRoot();
    }

    public void returnlist() {
        CommonConn conn = new CommonConn(getContext(), "store_list_return");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<StoreReturnListVO> returnlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreReturnListVO>>() {
            }.getType());
            binding.recvReturn.setAdapter(new ReturnAdapter(returnlist, getContext()));
            binding.recvReturn.setLayoutManager(new GridLayoutManager(getContext(),3));
        });
    }
}