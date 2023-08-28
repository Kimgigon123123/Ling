package com.example.ling.store;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ling.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class BankDialog extends BottomSheetDialog {

    LinearLayout into_bank1,into_bank2,into_bank3;
    ImageView  imgv_close;

    public BankDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_bank);
        imgv_close = findViewById(R.id.imgv_close);
        into_bank1 = findViewById(R.id.into_bank1);
        into_bank2 = findViewById(R.id.into_bank2);
        into_bank3 = findViewById(R.id.into_bank3);

        imgv_close.setOnClickListener(v->{
            dismiss();
        });

        into_bank1.setOnClickListener(v -> {

            Intent intent = new Intent(context,InsertBankInfoActivity.class);
            intent.putExtra("bankname","카카오뱅크");
            context.startActivity(intent);
            dismiss();

        });

        into_bank2.setOnClickListener(v->{
            Intent intent = new Intent(context,InsertBankInfoActivity.class);
            intent.putExtra("bankname","농협");
            context.startActivity(intent);
            dismiss();
        });

        into_bank3.setOnClickListener(v->{
            Intent intent = new Intent(context,InsertBankInfoActivity.class);
            intent.putExtra("bankname","광주은행");
            context.startActivity(intent);
            dismiss();
        });








            }



    }

