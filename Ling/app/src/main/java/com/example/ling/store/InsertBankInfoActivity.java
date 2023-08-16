package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityInsertBankInfoBinding;
import com.example.ling.store.myinfo.StoreMyinfoVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class InsertBankInfoActivity extends AppCompatActivity {

    ActivityInsertBankInfoBinding binding;
    String regi = "regi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertBankInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CommonConn conn2 = new CommonConn(this,"store_myinfo");
        conn2.addParamMap("id",CommonVar.loginInfo.getId());

        conn2.onExcute((isResult, data) -> {

            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {}.getType());

            binding.tvMyname.setText(list.get(0).getName());


        });


        //계좌 입력창 자동 포커스
        binding.edtBankNum.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

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
            if(binding.edtBankNum.getText().toString().length()!=13){
                Toast.makeText(this, "13자로 입력", Toast.LENGTH_SHORT).show();
            }else{
                CommonConn conn = new CommonConn(this , "store_update_bank_info");
                conn.addParamMap("bank" , bankname+": "+binding.edtBankNum.getText().toString().substring(0,3)+"-"+binding.edtBankNum.getText().toString().substring(3,7)+"-"+binding.edtBankNum.getText().toString().substring(7,11)+"-"+binding.edtBankNum.getText().toString().substring(11,13));
                conn.addParamMap("id", CommonVar.loginInfo.getId());

                conn.onExcute((isResult, data) -> {

                });
                finish();
            }

//            CompleteDialog dialog = new CompleteDialog(this,regi);
//            dialog.show();

        });

    }
}