package com.example.ling.store.myinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentBuylistBinding;
import com.example.ling.databinding.FragmentZZimBinding;
import com.example.ling.store.storeCO.StorePurchaseListVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class BuylistFragment extends Fragment {




    FragmentBuylistBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBuylistBinding.inflate(inflater,container,false);
        buylist();
//        binding.recvBuylist.setAdapter(new BuylistMoreAdapter(getContext()));
//        binding.recvBuylist.setLayoutManager(new GridLayoutManager(getContext(),3));
        return binding.getRoot();
    }

    public void buylist() {
        CommonConn conn = new CommonConn(getContext(), "list_purchase");
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {
            ArrayList<StorePurchaseListVO> buylistlist = new Gson().fromJson(data, new TypeToken<ArrayList<StorePurchaseListVO>>() {
            }.getType());
            binding.recvBuylist.setAdapter(new BuylistAdapter(buylistlist, getContext()));
            binding.recvBuylist.setLayoutManager(new GridLayoutManager(getContext(),4));

            if(buylistlist.size()==0){
                binding.tvEmpty.setVisibility(View.VISIBLE);
            }
        });
    }
}