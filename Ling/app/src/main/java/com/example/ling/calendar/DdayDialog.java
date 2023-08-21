package com.example.ling.calendar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.ling.R;

import java.util.Objects;

public class DdayDialog extends Dialog {


    public DdayDialog(@NonNull Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        getWindow().setGravity(Gravity.CENTER);

        setContentView(R.layout.ddaydialog);     //다이얼로그에서 사용할 레이아웃입니다.

        ImageView gifImageView = findViewById(R.id.loading_image);
        Glide.with(getContext()).asGif()
                .load(R.drawable.ddayalram)
                .into(gifImageView);

        Button btn_ddayAlarm = findViewById(R.id.btn_ddayalarm);
        btn_ddayAlarm.setOnClickListener(view -> {
            dismiss();
        });
    }
}
