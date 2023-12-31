package com.example.ling.store;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.example.ling.store.storeCO.StorePurchaseListVO;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;


public class BuyDialog extends BottomSheetDialog {

    int cnt=1;
    Button btn_buy,btn_basket;
    TextView tv_name,tv_price,tv_total_price,tv_cnt;
    ImageView imgv_close,imgv_up,imgv_down;

    public BuyDialog(@NonNull Context context,String name, int price,String item_code,String category_code) {
        super(context);
        setContentView(R.layout.dialog_buy);
        btn_buy = findViewById(R.id.btn_buy);
        btn_basket = findViewById(R.id.btn_basket);
        imgv_close = findViewById(R.id.imgv_close);
        tv_name = findViewById(R.id.tv_name);
        tv_name.setText(name);
        tv_price = findViewById(R.id.tv_price);

        // 가격 값을 쉼표 포함한 형식으로 포맷팅
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(price);
        tv_price.setText(formattedPrice+"");

        tv_cnt = findViewById(R.id.tv_cnt);
        imgv_up = findViewById(R.id.imgv_up);
        imgv_down = findViewById(R.id.imgv_down);
        tv_total_price = findViewById(R.id.tv_total_price);
        tv_total_price.setText(decimalFormat.format(price * cnt)+"");
        String payment="payment";

        imgv_up.setOnClickListener(v -> {
            cnt++;
            tv_cnt.setText(cnt + "");
            tv_total_price.setText(decimalFormat.format(price * cnt)+"");

        });

        imgv_down.setOnClickListener(v -> {

            if(cnt<=1){

                cnt++;
            }else{
                cnt--;
                tv_cnt.setText(cnt + "");
                tv_total_price.setText(decimalFormat.format(price * cnt)+"");
            }




        });


        btn_basket.setOnClickListener(v->{
            CommonConn conn = new CommonConn(context,"store_insert_basket");
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.addParamMap("item_code",item_code);
            conn.addParamMap("category_code",category_code);
            conn.addParamMap("selection",cnt);

                    conn.onExcute((isResult, data) -> {

                    });

            Toast.makeText(context, "장바구니에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
        });

        imgv_close.setOnClickListener(v->{
            dismiss();
        });


        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context,StorePaymentActivity.class);
                int priceWithoutCommas = Integer.parseInt(tv_total_price.getText().toString().replace(",", ""));
                intent.putExtra("price", priceWithoutCommas);
                intent.putExtra("item_code",item_code);
                intent.putExtra("cnt",cnt);
                intent.putExtra("category_code",category_code);
                context.startActivity(intent);
                dismiss();



            }
        });


    }


}
