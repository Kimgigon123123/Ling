package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.databinding.ActivityChargeCashBinding;
import com.example.ling.databinding.ActivityStorePaymentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ChargeCashActivity extends AppCompatActivity {

    ActivityChargeCashBinding binding;

    String intoPayment=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding= ActivityChargeCashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());








        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnInputBank.setOnClickListener(v -> {


            BottomSheetDialog bottomSheetDialog = new BankDialog(this);
            bottomSheetDialog.show();


        });

        binding.btnCharge.setOnClickListener(v -> {

//            if(intoPayment.equals("intoPayment")){
                Dialog dialog = new CompleteDialog(this,"charge");
                dialog.show();
//            }
//            else if(intoMyinfo.equals("intoMyinfo")){
////                Intent intent2 = new Intent(ChargeCashActivity.this,StoreMyinfoActivity.class);
////                startActivity(intent2);
//                Dialog dialog = new CompleteDialog(this,"intoMyinfo");
//                dialog.show();
//
//            }


        });

    }
}