package com.example.ling.store;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ling.R;
import com.example.ling.store.myinfo.StoreMyinfoActivity;



public class CompleteDialog extends Dialog {



    public CompleteDialog(@NonNull Context context,String str) {


        super(context);
        setContentView(R.layout.dialog_complete);

        if(str.equals("BuyComplete")){
            TextView tv = findViewById(R.id.tv);
            tv.setText("구매 완료 되었습니다.");
        }

        if(str.equals("regi")){
            TextView tv = findViewById(R.id.tv);
            tv.setText("등록 완료 되었습니다.");
        }
        else if(str.equals("charge")){
            TextView tv = findViewById(R.id.tv);
            tv.setText("충전 완료 되었습니다.");
        }
        else if(str.equals("isMyinfo")){
            TextView tv = findViewById(R.id.tv);
            tv.setText("충전 완료 되었습니다.");
        }else if(str.equals("return")){
            TextView tv = findViewById(R.id.tv);
            tv.setText("반품처리가 요청 되었습니다. \n빠른 시일내에 처리해드리겠습니다.");
        }



        Button btn_yes = findViewById(R.id.btn_yes);

        btn_yes.setOnClickListener(v -> {
            if(str.equals("regi")){
                Intent intent = new Intent(context,ChargeCashActivity.class);
                context.startActivity(intent);

                dismiss();
            }
            else if(str.equals("charge")){
//                Intent intent = new Intent(context, StorePaymentActivity.class);
//                context.startActivity(intent);
                dismiss();
            }
            else if(str.equals("intoMyinfo")){
                Intent intent = new Intent(context, StoreMyinfoActivity.class);
                context.startActivity(intent);
                dismiss();
            }

            else if(str.equals("BuyComplete")){
//                Intent intent = new Intent(context, StorePaymentActivity.class);
//                context.startActivity(intent);
                dismiss();
            }else if(str.equals("return")){
                dismiss();
            }

        });



    }
}
