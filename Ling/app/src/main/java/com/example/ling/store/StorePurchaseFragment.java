package com.example.ling.store;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.example.ling.databinding.FragmentStoreCoBinding;
import com.example.ling.databinding.FragmentStorePurchaseBinding;
import com.example.ling.databinding.FragmentStorePurchaseBinding;
import com.example.ling.store.myinfo.StoreMyinfoActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class StorePurchaseFragment extends Fragment {

    FragmentStorePurchaseBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = FragmentStorePurchaseBinding.inflate(inflater,container,false);



        binding.imgvMyinfo.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), StoreMyinfoActivity.class);
            getContext().startActivity(intent);
        });


        binding.btnBuy.setOnClickListener(v -> {


            BottomSheetDialog bottomSheetDialog = new BuyDialog(this.getContext(),"이름",1000);
            bottomSheetDialog.show();


        });

        binding.btnZzim.setOnClickListener(v->{

        });
        return binding.getRoot();
    }
}