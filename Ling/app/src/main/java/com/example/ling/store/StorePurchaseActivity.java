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
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

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
        String item_code = intent.getStringExtra("item_code");
        String imageUrl=intent.getStringExtra("item_img");
        String category_code = intent.getStringExtra("category_code");

            binding.tvContent.setText(content);
            binding.tvName.setText(name);
            binding.tvPrice.setText(price+"");

        Picasso.get()
                .load(imageUrl)
                .into(binding.imgv);



        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });


        binding.btnBuy.setOnClickListener(v -> {


            BottomSheetDialog bottomSheetDialog = new BuyDialog(this,name,price,item_code,category_code);
            bottomSheetDialog.show();


        });

        binding.btnZzim.setOnClickListener(v->{

                CommonConn conn = new CommonConn(this , "store_insert_zzim");
                conn.addParamMap("item_code" , item_code);
                conn.addParamMap("id", CommonVar.loginInfo.getId());
                conn.onExcute((isResult, data) -> {

                });
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