package com.example.ling.capsule;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LetterDialog extends Dialog {

    TextView tv_title,tv_content,tv_date,tv_name;
    public LetterDialog(@NonNull Context context,int i) {
        super(context);
        setContentView(R.layout.dialog_letter);

        tv_title=findViewById(R.id.tv_title);
        tv_content=findViewById(R.id.tv_content);
        tv_date=findViewById(R.id.tv_date);
        tv_name=findViewById(R.id.tv_name);

        CommonConn conn = new CommonConn(getContext(), "letter_select");
        conn.addParamMap("couple_num" , CommonVar.loginInfo.getCouple_num());
        conn.onExcute((isResult, data) -> {
            ArrayList<CapsuleVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<CapsuleVO>>() {
            }.getType());

            tv_title.setText(list.get(i).getTc_title());
            tv_content.setText(list.get(i).getTc_content());
            tv_date.setText(list.get(i).getCreate_date());
            tv_name.setText("from."+list.get(i).getName());

        });




    }
}
