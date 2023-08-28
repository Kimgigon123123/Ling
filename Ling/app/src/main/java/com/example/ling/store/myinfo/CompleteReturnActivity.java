package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityCompleteReturnBinding;
import com.example.ling.databinding.ActivityDeliveryBinding;
import com.example.ling.databinding.ActivityReturnBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompleteReturnActivity extends AppCompatActivity {

    ActivityCompleteReturnBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCompleteReturnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int intValue = getIntent().getIntExtra("order_num",0);
//        Toast.makeText(this, intValue+"", Toast.LENGTH_SHORT).show();

        CommonConn conn = new CommonConn(this , "store_return_detail");
        conn.addParamMap("order_num" , intValue);
        


        conn.onExcute((isResult, data) -> {
            ArrayList<StoreReturnListVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreReturnListVO>>() {}.getType());

            String imageUrl =list.get(0).getItem_img();
            Picasso.get()
                    .load(imageUrl)
                    .into(binding.imgvItem);

            binding.tvName.setText(list.get(0).getItem_name()+"");
            binding.tvCnt.setText(list.get(0).getPurchase_cnt()+"개");
            binding.tvAddress.setText(list.get(0).getAddress());
            binding.tvPrice.setText(list.get(0).getItem_price()+"원");
            binding.tvTotalPrice.setText(list.get(0).getTotal_price()+"원");
            binding.tvReturnState.setText(list.get(0).getReturn_state());




        });

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

    }
}