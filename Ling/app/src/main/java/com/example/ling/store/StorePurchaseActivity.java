package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class StorePurchaseActivity extends AppCompatActivity {

    ActivityStorePurchaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStorePurchaseBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        setContentView(binding.getRoot());

        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");
        int price = intent.getIntExtra("price",0);

            binding.tvContent.setText(content);
            binding.tvName.setText(name);
            binding.tvPrice.setText(price+"");









        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });


        binding.btnBuy.setOnClickListener(v -> {


            BottomSheetDialog bottomSheetDialog = new BuyDialog(this,name,price);
            bottomSheetDialog.show();


        });

        binding.btnZzim.setOnClickListener(v->{
            Toast.makeText(this, "찜목록에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
        });


    }

    protected void onRestart() {
        super.onRestart();
        if(ChargeVO.isBuy){
            Dialog dialog = new CompleteDialog(this,"BuyComplete");
            dialog.show();
            ChargeVO.isBuy=false;
        }


    }
}