package com.example.ling.store;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ling.R;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class BuyDialog extends BottomSheetDialog {


    Button btn_buy;

    public BuyDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_buy);
        btn_buy=findViewById(R.id.btn_buy);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context,StorePaymentActivity.class);
                context.startActivity(intent);


            }
        });


    }
}
