package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityChargeCashBinding;
import com.example.ling.databinding.ActivityStorePaymentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ChargeCashActivity extends AppCompatActivity {

    ActivityChargeCashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChargeCashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String charge = "charge";

        binding.btnInputBank.setOnClickListener(v -> {


            BottomSheetDialog bottomSheetDialog = new BankDialog(this);
            bottomSheetDialog.show();

        });

        binding.btnCharge.setOnClickListener(v -> {
            Dialog dialog = new CompleteDialog(this,charge);
            dialog.show();
        });

    }
}