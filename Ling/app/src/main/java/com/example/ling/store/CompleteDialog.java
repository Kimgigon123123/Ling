package com.example.ling.store;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ling.R;

public class CompleteDialog extends Dialog {


    public CompleteDialog(@NonNull Context context,String str) {


        super(context);
        setContentView(R.layout.dialog_complete);



        if(str.equals("regi")){
            TextView tv = findViewById(R.id.tv);
            tv.setText("등록완료~~~~!!!");
        }
        else if(str.equals("charge")){
            TextView tv = findViewById(R.id.tv);
            tv.setText("충전완료~~~~!!!");
        }



        Button btn_yes = findViewById(R.id.btn_yes);

        btn_yes.setOnClickListener(v -> {
            if(str.equals("regi")){
                Intent intent = new Intent(context,ChargeCashActivity.class);
                context.startActivity(intent);

                dismiss();
            }
            else if(str.equals("charge")){
                Intent intent = new Intent(context, StorePaymentActivity.class);
                context.startActivity(intent);
                dismiss();
            }

        });



    }
}
