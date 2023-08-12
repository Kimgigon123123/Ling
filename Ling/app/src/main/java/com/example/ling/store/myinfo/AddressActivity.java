package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityAddressBinding;
import com.example.ling.databinding.ActivityStorePaymentBinding;

public class AddressActivity extends AppCompatActivity {


    ActivityAddressBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.edtAddress.setEnabled(false);
    }
}