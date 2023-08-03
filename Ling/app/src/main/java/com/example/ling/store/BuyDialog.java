package com.example.ling.store;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ling.R;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class BuyDialog extends BottomSheetDialog {


    Button btn_buy,btn_basket;
    ImageView imgv_close;

    public BuyDialog(@NonNull Context context,String name, int price) {
        super(context);
        setContentView(R.layout.dialog_buy);
        btn_buy=findViewById(R.id.btn_buy);
        btn_basket=findViewById(R.id.btn_basket);
        imgv_close=findViewById(R.id.imgv_close);
        String payment="payment";

        btn_basket.setOnClickListener(v->{
            Toast.makeText(context, "장바구니에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
        });

        imgv_close.setOnClickListener(v->{
            dismiss();
        });


        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context,StorePaymentActivity.class);
                intent.putExtra("payment",payment);
                context.startActivity(intent);


            }
        });


    }


}
