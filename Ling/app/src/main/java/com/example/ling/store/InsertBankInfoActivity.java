package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityInsertBankInfoBinding;

public class InsertBankInfoActivity extends AppCompatActivity {

    ActivityInsertBankInfoBinding binding;
    String regi = "regi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertBankInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String bankname;
        bankname = intent.getStringExtra("bankname");

        if(bankname.equals("카카오뱅크")){
           binding.imgvBankImg.setImageResource(R.drawable.kakaobank);
           binding.tvBankName.setText("카카오뱅크");
        } else if (bankname.equals("농협")) {
            binding.imgvBankImg.setImageResource(R.drawable.nh);
            binding.tvBankName.setText("농협");
        }else if (bankname.equals("광주은행")) {
            binding.imgvBankImg.setImageResource(R.drawable.gwangju);
            binding.tvBankName.setText("광주은행");
        }

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnRegi.setOnClickListener(v -> {
            CommonConn conn = new CommonConn(this , "store_update_bank_info");
            conn.addParamMap("bank" , bankname+": "+binding.edtBankNum.getText().toString());
            conn.addParamMap("id", CommonVar.loginInfo.getId());

            conn.onExcute((isResult, data) -> {

            });
            finish();
//            CompleteDialog dialog = new CompleteDialog(this,regi);
//            dialog.show();

        });

    }
}