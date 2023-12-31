package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityReturnBinding;
import com.example.ling.store.ChargeVO;
import com.example.ling.store.CompleteDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ReturnActivity extends AppCompatActivity {

    ActivityReturnBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityReturnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int intValue = getIntent().getIntExtra("order_num",0);
//        Toast.makeText(this, intValue+"", Toast.LENGTH_SHORT).show();

        AtomicInteger return_money= new AtomicInteger();
        CommonConn conn = new CommonConn(this , "store_return");
        conn.addParamMap("order_num" , intValue);
        conn.onExcute((isResult, data) -> {
            ArrayList<StoreReturnVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreReturnVO>>() {}.getType());
            binding.tvName.setText(list.get(0).getItem_name()+"");
            binding.tvReturnState.setText(list.get(0).getDelivery_state());


            double price = list.get(0).getItem_price();

// 가격 값을 쉼표 포함한 형식으로 포맷팅
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedPrice = decimalFormat.format(price);
// TextView에 쉼표 처리된 가격 설정
            binding.tvPrice.setText(formattedPrice+"원");

//            binding.tvPrice.setText(list.get(0).getItem_price()+"원");
            binding.tvCnt.setText(list.get(0).getPurchase_cnt()+"개");
            double totalPrice = list.get(0).getTotal_price();

// 가격 값을 쉼표 포함한 형식으로 포맷팅
            DecimalFormat decimalFormat2 = new DecimalFormat("#,###");
            String formattedTotalPrice = decimalFormat2.format(totalPrice);

// TextView에 쉼표 처리된 가격 설정
            binding.tvTotalPrice.setText(formattedTotalPrice + "원");
            binding.tvTotalReturnPrice.setText(formattedTotalPrice + "원");
            binding.tvAddress.setText(list.get(0).getAddress());
            return_money.set(list.get(0).getTotal_price());


            String imageUrl =list.get(0).getItem_img();
            Picasso.get()
                    .load(imageUrl)
                    .into(binding.imgvItem);
        });




        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnCancle.setOnClickListener(v->{
            finish();
        });

        binding.btnReturn.setOnClickListener(v->{
//            Toast.makeText(this, "반품요청 처리가 되었습니다.", Toast.LENGTH_SHORT).show();

            ChargeVO.isReturn=true;

//            CommonConn conn2 = new CommonConn(this , "store_return_money");
//            conn2.addParamMap("id", CommonVar.loginInfo.getId());
//            conn2.addParamMap("money" , return_money);
//            conn2.onExcute((isResult, data) -> {
//
//            });

            CommonConn conn3 = new CommonConn(this , "store_insert_return");
            conn3.addParamMap("order_num" , intValue);
            conn3.onExcute((isResult, data) -> {

            });

            CommonConn conn4 = new CommonConn(this , "store_buylist_delete");
            conn4.addParamMap("order_num" , intValue);
            conn4.onExcute((isResult, data) -> {

            });


            Intent intent = new Intent(ReturnActivity.this, StoreMyinfoActivity.class);
            intent.putExtra("order_num" , intValue);
            startActivity(intent);
            finish();
        });

    }
}