package com.example.ling.store;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.ling.R;

public class NoMoneyDialog extends Dialog {
    public NoMoneyDialog(@NonNull Context context) {
        super(context);

        setContentView(R.layout.dialog_nomoney);

        Button btn_no = findViewById(R.id.btn_no);
        Button btn_yes = findViewById(R.id.btn_yes);



        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChargeCashActivity.class);
                context.startActivity(intent);
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( context,StorePurchaseActivity.class);
                context.startActivity(intent);

            }
        });

    }
}
