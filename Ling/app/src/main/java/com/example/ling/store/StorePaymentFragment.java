package com.example.ling.store;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentStorePaymentBinding;
import com.example.ling.databinding.FragmentStorePurchaseBinding;


public class StorePaymentFragment extends Fragment {

   FragmentStorePaymentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStorePaymentBinding.inflate(inflater,container,false);


        binding.btnBack.setOnClickListener(v -> {

            Intent intent = new Intent (getContext(),StorePurchaseActivity.class);
            startActivity(intent);

        });

        binding.btnBuy.setOnClickListener(v->{

            NoMoneyDialog dialog = new NoMoneyDialog(getContext());
            dialog.show();


        });

        return binding.getRoot();
    }
}