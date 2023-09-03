package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityChargeCashBinding;
import com.example.ling.databinding.ActivityStorePaymentBinding;
import com.example.ling.store.myinfo.StoreMyinfoVO;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ChargeCashActivity extends AppCompatActivity {

    ActivityChargeCashBinding binding;

    String intoPayment=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding= ActivityChargeCashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //금액 입력창 자동 포커스
        binding.edtMoney.requestFocus();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        select();

        CommonConn conn = new CommonConn(this , "store_select_bank");
        conn.addParamMap("id",CommonVar.loginInfo.getId());

        conn.onExcute((isResult, data) -> {

            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<StoreMyinfoVO>>(){}.getType());
            binding.tvBankInfo.setText(list.get(0).getBank());


        });





        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnInputBank.setOnClickListener(v -> {

            BottomSheetDialog bottomSheetDialog = new BankDialog(this);
            bottomSheetDialog.show();


        });

        binding.btnCharge.setOnClickListener(v -> {

            CommonConn conn3 = new CommonConn(this , "store_select_bank");
            conn3.addParamMap("id",CommonVar.loginInfo.getId());

            conn3.onExcute((isResult, data) -> {

                ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<StoreMyinfoVO>>(){}.getType());
                if(list.get(0).getBank().equals(" ")){
                    Toast.makeText(this, "계좌번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{

                    try {
                        CommonConn conn2 = new CommonConn(this,"store_charge");
                        conn2.addParamMap("id",CommonVar.loginInfo.getId());
                        conn2.addParamMap("edtMoney",Integer.parseInt(binding.edtMoney.getText().toString()));//"0" -> 0 : ""->
                        conn2.onExcute((isResult2, data2) -> {


                        });

                        ChargeVO.isCharge=true;
                        finish();


                    }catch (Exception e){
                        Toast.makeText(this, "금액을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        });

    }


    public void select(){
        CommonConn conn = new CommonConn(this,"store_myinfo");
        conn.addParamMap("id",CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {}.getType());


            binding.tvMoney.setText(formatMoneyWithCommas(list.get(0).getMoney()) + "");


        });
    }

    protected void onRestart() {
        super.onRestart();

        CommonConn conn = new CommonConn(this , "store_select_bank");
        conn.addParamMap("id",CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {

            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<StoreMyinfoVO>>(){}.getType());
            binding.tvBankInfo.setText(list.get(0).getBank());
        });
    }

    private String formatMoneyWithCommas(int money) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        return nf.format(money);
    }



}