package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class StorePurchaseActivity extends AppCompatActivity {

    ActivityStorePurchaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStorePurchaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnBuy.setOnClickListener(v -> {


            BottomSheetDialog bottomSheetDialog = new BuyDialog(this);
            bottomSheetDialog.show();


        });

        binding.btnZzim.setOnClickListener(v->{

        });


    }
}