package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityInsertBankInfoBinding;

public class InsertBankInfoActivity extends AppCompatActivity {

    ActivityInsertBankInfoBinding binding;
    String regi = "regi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertBankInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegi.setOnClickListener(v -> {

            CompleteDialog dialog = new CompleteDialog(this,regi);
            dialog.show();

        });

    }
}