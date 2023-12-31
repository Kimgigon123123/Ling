package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.ling.R;
import com.example.ling.Static;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityStoreMyinfoBinding;
import com.example.ling.store.ChargeCashActivity;
import com.example.ling.store.ChargeVO;
import com.example.ling.store.CompleteDialog;
import com.example.ling.store.StoreCoAdater;
import com.example.ling.store.basket.BasketActivity;
import com.example.ling.store.storeCO.StoreCOVO;
import com.example.ling.store.storeCO.StorePurchaseListVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;

public class StoreMyinfoActivity extends AppCompatActivity {




    ActivityStoreMyinfoBinding binding;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        binding=ActivityStoreMyinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());









        select();
        zzimlist();
        buylist();
        returnlist();

        if(ChargeVO.isReturn){
            Dialog dialog = new CompleteDialog(this,"return");
            dialog.show();
            ChargeVO.isReturn=false;
        }




//



        binding.btnAddress.setOnClickListener(v->{
            Intent intent = new Intent(this, AddressMainActivity.class);
            startActivity(intent);
        });


        binding.btnZzim.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(this, ZZimActivity.class);
            startActivity(intent);
        });

        binding.btnBasket.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(this, BasketActivity.class);
            startActivity(intent);
        });


        binding.btnMyinfo.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(this, StoreMyinfoActivity.class);
            startActivity(intent);
        });

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnCharge.setOnClickListener(v->{


            Intent intent = new Intent(StoreMyinfoActivity.this, ChargeCashActivity.class);
            startActivity(intent);
        });

        binding.imgvIntoZZim.setOnClickListener(v->{
            Intent intent = new Intent(StoreMyinfoActivity.this, ZZimActivity.class);
            startActivity(intent);
        });

        binding.imgvIntoBylist.setOnClickListener(v->{
            Intent intent = new Intent(this, ZZimActivity.class);
            intent.putExtra("selected_chip", 2);
            startActivity(intent);
        });

        binding.imgvReturn.setOnClickListener(v->{
            Intent intent = new Intent(StoreMyinfoActivity.this, ZZimActivity.class);
            intent.putExtra("selected_chip", 3);
            startActivity(intent);
        });

    }

    public void select(){
        CommonConn conn = new CommonConn(this,"store_myinfo");
        conn.addParamMap("id",CommonVar.loginInfo.getId());

        conn.onExcute((isResult, data) -> {

            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {}.getType());


            String imageUrl = list.get(0).profile;

            if (imageUrl != null && !imageUrl.equals(" ")) {
                Picasso.get()
                        .load(imageUrl)
                        .into(binding.imgvProfile);
            } else {
                // URL이 없는 경우 기본 이미지를 설정
                Picasso.get()
                        .load(R.drawable.profile_img)
                        .into(binding.imgvProfile);
            }

            binding.tvMoney.setText(formatPriceWithCommas(list.get(0).getMoney()) + "");
            binding.tvName.setText(list.get(0).getName());
            binding.tvAddress.setText(list.get(0).getAddress()+list.get(0).getDetail_add());




        });
    }




    public void zzimlist() {
        CommonConn conn = new CommonConn(this, "store_list_zzim");
        conn.addParamMap("id",CommonVar.loginInfo.getId());

        conn.onExcute((isResult, data) -> {

            ArrayList<StoreZzimListVO> zzimlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreZzimListVO>>() {
            }.getType());
            binding.recvZzim.setAdapter(new ZZimAdapter(zzimlist,this));
            binding.recvZzim.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

            if(zzimlist.size()==0){
                binding.tvEmpty1.setVisibility(View.VISIBLE);
            }



//            binding.recvReturn.setAdapter(new ReturnAdapter(this));
//            binding.recvReturn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


        });
    }

    public void buylist() {
        CommonConn conn = new CommonConn(this, "list_purchase");
        conn.addParamMap("id",CommonVar.loginInfo.getId());
        binding.tvEmpty2.setVisibility(View.INVISIBLE);

        conn.onExcute((isResult, data) -> {
            ArrayList<StorePurchaseListVO> buylistlist = new Gson().fromJson(data, new TypeToken<ArrayList<StorePurchaseListVO>>() {
            }.getType());
            binding.recvBuylist.setAdapter(new BuylistAdapter(buylistlist, this));
            binding.recvBuylist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            if(buylistlist.size()==0){
                binding.tvEmpty2.setVisibility(View.VISIBLE);
            }

        });
    }


    public void returnlist() {
        CommonConn conn = new CommonConn(this, "store_list_return");
        conn.addParamMap("id",CommonVar.loginInfo.getId());

        conn.onExcute((isResult, data) -> {
            ArrayList<StoreReturnListVO> returnlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreReturnListVO>>() {
            }.getType());
            binding.recvReturn.setAdapter(new ReturnAdapter(returnlist, this));
            binding.recvReturn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            if(returnlist.size()==0){
                binding.tvEmpty3.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(ChargeVO.isCharge){
            Dialog dialog = new CompleteDialog(this,"charge");
            dialog.show();
            ChargeVO.isCharge=false;
        }


        if(ChargeVO.isReturn){
            Dialog dialog = new CompleteDialog(this,"return");
            dialog.show();
            ChargeVO.isReturn=false;
        }


        select();
        zzimlist();
        buylist();
        returnlist();




    }

    private String formatPriceWithCommas(int price) {
        return NumberFormat.getInstance().format(price);
    }


}