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
import com.example.ling.store.storeCO.StorePurchaseListVO;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StorePurchaseActivity extends AppCompatActivity {

    ActivityStorePurchaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStorePurchaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();




        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");
        int price = intent.getIntExtra("price",0);
        String item_code = intent.getStringExtra("item_code");
        String imageUrl=intent.getStringExtra("item_img");
        String category_code = intent.getStringExtra("category_code");

            binding.tvContent.setText(content);
            binding.tvName.setText(name);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(price);

// TextView에 쉼표 처리된 가격 설정
        binding.tvPrice.setText(formattedPrice);

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

            CommonConn conn = new CommonConn(this, "store_insert_zzim");
            conn.addParamMap("item_code", item_code);
            conn.addParamMap("category_code", category_code);
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.onExcute((isResult, data) -> {
                if (!isResult) {

                } else {

                    if (data == null) {

                        // 매핑 연결에 실패한 경우 (무결성 제약 조건 위배)
                        Toast.makeText(this, "이미 찜목록에 존재합니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        // 매핑 연결에 성공한 경우
                        Toast.makeText(this, "찜목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                }
            });

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