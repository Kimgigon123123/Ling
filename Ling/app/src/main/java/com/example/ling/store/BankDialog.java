package com.example.ling.store;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ling.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class BankDialog extends BottomSheetDialog {

    LinearLayout into_bank1;

    public BankDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_bank);

        into_bank1 = findViewById(R.id.into_bank1);

        into_bank1.setOnClickListener(v -> {

            Intent intent = new Intent(context,InsertBankInfoActivity.class);
            context.startActivity(intent);
            dismiss();

        });








            }



    }

