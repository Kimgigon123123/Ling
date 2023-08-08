package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityBasketBinding;
import com.example.ling.databinding.ActivityDeliveryBinding;
import com.example.ling.store.BuyDialog;
import com.example.ling.store.StorePurchaseActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DeliveryActivity extends AppCompatActivity {

    ActivityDeliveryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDeliveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int intValue = getIntent().getIntExtra("order_num",0);
        Toast.makeText(this, intValue+"", Toast.LENGTH_SHORT).show();

        CommonConn conn = new CommonConn(this , "store_return");
        conn.addParamMap("order_num" , intValue);

        conn.onExcute((isResult, data) -> {
            ArrayList<StoreReturnVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreReturnVO>>() {}.getType());

            String imageUrl =list.get(0).getItem_img();
            Picasso.get()
                    .load(imageUrl)
                    .into(binding.imgvItem);

            binding.tvName.setText(list.get(0).getItem_name()+"");
            binding.tvCnt.setText(list.get(0).getPurchase_cnt()+"개");
            binding.tvDeliveryState.setText(list.get(0).getDelivery_state());
            binding.tvPrice.setText(list.get(0).getItem_price()+"원");
            binding.tvTotalPrice.setText("총 "+list.get(0).getTotal_price()+"원");
        });


        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnBuy.setOnClickListener(v->{
            CommonConn conn2 = new CommonConn(this , "store_return");
            conn2.addParamMap("order_num" , intValue);

            conn.onExcute((isResult, data) -> {
                ArrayList<StoreReturnVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreReturnVO>>() {}.getType());
                BottomSheetDialog bottomSheetDialog = new BuyDialog(this,list.get(0).getItem_name(),list.get(0).getItem_price(),list.get(0).getItem_code(),"Co");
                bottomSheetDialog.show();
            });


        });

        binding.btnReturn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReturnActivity.class);
            intent.putExtra("order_num",intValue);
            startActivity(intent);
        });


    }
}