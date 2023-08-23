package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityStorePaymentBinding;
import com.example.ling.store.basket.StoreBasketVO;
import com.example.ling.store.myinfo.AddressActivity;
import com.example.ling.store.myinfo.AddressMainActivity;
import com.example.ling.store.myinfo.DetailAddActivity;
import com.example.ling.store.myinfo.StoreMyinfoVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class StorePaymentActivity extends AppCompatActivity {

    ActivityStorePaymentBinding binding;

    int totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityStorePaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CommonConn conn4 = new CommonConn(this,"store_select_detailadd");
        conn4.addParamMap("id",CommonVar.loginInfo.getId());

        conn4.onExcute((isResult, data) -> {
            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<StoreMyinfoVO>>(){}.getType());
            binding.tvInsertDetailAdd.setText(list.get(0).getDetail_add());

        });


        CommonConn conn2 = new CommonConn(this,"store_select_address");
        conn2.addParamMap("id",CommonVar.loginInfo.getId());

        conn2.onExcute((isResult, data) -> {
            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<StoreMyinfoVO>>(){}.getType());
           binding.tvInsertAdrress.setText(list.get(0).getAddress());

        });

        Intent intent3 = getIntent();
        int basket_total_price = intent3.getIntExtra("basket_total_price",0);

        if(basket_total_price != 0){
            binding.tvPrice.setText(basket_total_price+"원");
            binding.tvTotalPrice.setText(basket_total_price+3000+"원");
            totalPrice = basket_total_price+3000;

            binding.btnBuy.setOnClickListener(v->{
//                Toast.makeText(this, "장바구니에서 구매 버튼 누름", Toast.LENGTH_SHORT).show();

                if(binding.tvInsertAdrress.getText().toString().equals("주소를 입력해주세요.")){
                    Toast.makeText(this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{

                    CommonConn conn = new CommonConn(this, "store_myinfo");
                    conn.addParamMap("id",CommonVar.loginInfo.getId());

                    conn.onExcute((isResult, data) -> {

                        ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {
                        }.getType());

                        if (totalPrice > list.get(0).getMoney()) {
                            NoMoneyDialog dialog = new NoMoneyDialog(this);
                            dialog.show();
                        } else {
                            basket_buy();
                            Intent intent = new Intent(StorePaymentActivity.this, MainActivity.class);
                            startActivity(intent);
                            ChargeVO.isBuy = true;
                        }
                    });
                }
            });

        }

        else {

            Intent intent2 = getIntent();
            int price = intent2.getIntExtra("price", 0);
            binding.tvPrice.setText(price + "원");

            totalPrice = price + 3000;
            binding.tvTotalPrice.setText(totalPrice + "원");


            binding.btnBuy.setOnClickListener(v -> {

                        if(binding.tvInsertAdrress.getText().toString().equals("주소를 입력해주세요.")){
                            Toast.makeText(this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }else{

                            CommonConn conn = new CommonConn(this, "store_myinfo");
                            conn.addParamMap("id",CommonVar.loginInfo.getId());

                            conn.onExcute((isResult, data) -> {

                                ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {
                                }.getType());

                                if (totalPrice > list.get(0).getMoney()) {
                                    NoMoneyDialog dialog = new NoMoneyDialog(this);
                                    dialog.show();
                                } else {
                                    buy();
                                    ChargeVO.isBuy = true;
                                    finish();
                                }
                            });

                        }

            });

        }

        select();


        if (ChargeVO.isCharge) {
            Dialog dialog = new CompleteDialog(this, "charge");
            dialog.show();
            ChargeVO.isCharge = false;
        }


        binding.imgvBefore.setOnClickListener(v -> {

            finish();


        });



        binding.imgvIntoAdrress.setOnClickListener(v -> {
            Intent intent = new Intent(StorePaymentActivity.this, AddressMainActivity.class);
            startActivity(intent);
        });

        binding.imgvIntoDetailAdd.setOnClickListener(v->{
            Intent intent = new Intent(StorePaymentActivity.this, DetailAddActivity.class);
            startActivity(intent);
        });

        binding.btnCharge.setOnClickListener(v->{
            Intent intent = new Intent(StorePaymentActivity.this,ChargeCashActivity.class);
            startActivity(intent);
        });
    }

    protected void onRestart() {
        super.onRestart();

        if(ChargeVO.isCharge){
            Dialog dialog = new CompleteDialog(this,"charge");
            dialog.show();
            ChargeVO.isCharge=false;
        }


        CommonConn conn4 = new CommonConn(this,"store_select_detailadd");
        conn4.addParamMap("id",CommonVar.loginInfo.getId());

        conn4.onExcute((isResult, data) -> {
            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<StoreMyinfoVO>>(){}.getType());
            binding.tvInsertDetailAdd.setText(list.get(0).getDetail_add());

        });


        CommonConn conn2 = new CommonConn(this,"store_select_address");
        conn2.addParamMap("id",CommonVar.loginInfo.getId());

        conn2.onExcute((isResult, data) -> {
            Log.d("결과값", "select: "+data);
            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<StoreMyinfoVO>>(){}.getType());
            binding.tvInsertAdrress.setText(list.get(0).getAddress());

        });


        CommonConn conn = new CommonConn(this, "store_myinfo");
        conn.addParamMap("id",CommonVar.loginInfo.getId());

        conn.onExcute((isResult, data) -> {
            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {
            }.getType());


            binding.tvMymoney.setText(list.get(0).getMoney() + "");

        });
    }

    public void select(){
        CommonConn conn = new CommonConn(this, "store_myinfo");
        conn.addParamMap("id",CommonVar.loginInfo.getId());

        conn.onExcute((isResult, data) -> {
            ArrayList<StoreMyinfoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMyinfoVO>>() {}.getType());


            binding.tvMymoney.setText(list.get(0).getMoney()+"");

    });
}

    public void buy(){

            CommonConn conn = new CommonConn(this, "store_buy");
            conn.addParamMap("totalPrice",totalPrice);
            conn.addParamMap("id",CommonVar.loginInfo.getId());
            conn.onExcute((isResult, data) -> {


                Intent intent = getIntent();
                String item_code = intent.getStringExtra("item_code");
                int cnt = intent.getIntExtra("cnt",0);
                String category_code = intent.getStringExtra("category_code");

                CommonConn conn5 = new CommonConn(this,"store_sales_up");
                conn5.addParamMap("item_code",item_code);
                conn5.addParamMap("purchase_cnt",cnt);
                conn5.onExcute(((isResult5, data5) -> {

                }));

                CommonConn conn2 = new CommonConn(this , "insert_purchase");
                conn2.addParamMap("id",CommonVar.loginInfo.getId());
                conn2.addParamMap("item_code" , item_code);
                conn2.addParamMap("purchase_cnt" , cnt);
                conn2.addParamMap("category_code" ,category_code);
                if(binding.tvInsertDetailAdd.getText().toString().equals("상세주소를 입력해주세요.")){
                    conn2.addParamMap("address",binding.tvInsertAdrress.getText().toString());
                    conn2.onExcute((isResult2, data2) -> {

                    });
                }else{
                    conn2.addParamMap("address",binding.tvInsertAdrress.getText().toString()+" "+binding.tvInsertDetailAdd.getText().toString());
                    conn2.onExcute((isResult2, data2) -> {

                    });
                }


            });

    }
    public void basket_buy(){
        CommonConn conn = new CommonConn(this, "store_buy");
        conn.addParamMap("totalPrice",totalPrice);
        conn.addParamMap("id",CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {

            CommonConn conn6 = new CommonConn(this,"store_list_basket");
            conn6.addParamMap("id",CommonVar.loginInfo.getId());
            conn6.onExcute((isResult6, data6) -> {
                ArrayList<StoreBasketVO> list = new Gson().fromJson(data6, new TypeToken<ArrayList<StoreBasketVO>>() {}.getType());

                CommonConn conn7 = new CommonConn(this,"store_sales_up");
                for(int i=0;i<list.size();i++){
                    conn7.addParamMap("item_code",list.get(i).getItem_code());
                    conn7.addParamMap("purchase_cnt",list.get(i).getSelection());
                    conn7.onExcute(((isResult7, data7) -> {

                    }));
                }

                    });

            CommonConn conn2 = new CommonConn(this , "insert_basket_buylist");
            conn2.addParamMap("id",CommonVar.loginInfo.getId());
            if(binding.tvInsertDetailAdd.getText().toString().equals("상세주소를 입력해주세요.")){
                conn2.addParamMap("address",binding.tvInsertAdrress.getText().toString());
                conn2.onExcute((isResult2, data2) -> {

                    CommonConn conn3 = new CommonConn(this , "delete_basket_buylist");
                    conn3.addParamMap("id",CommonVar.loginInfo.getId());
                    conn3.onExcute((isResult3,data3)->{


                    });

                });
            }else{
                conn2.addParamMap("address",binding.tvInsertAdrress.getText().toString()+" "+binding.tvInsertDetailAdd.getText().toString());
                conn2.onExcute((isResult2, data2) -> {

                    CommonConn conn3 = new CommonConn(this , "delete_basket_buylist");
                    conn3.addParamMap("id",CommonVar.loginInfo.getId());
                    conn3.onExcute((isResult3,data3)->{
                    });

                });
            }

//            conn2.onExcute((isResult2,data2)->{


//                CommonConn conn3 = new CommonConn(this , "delete_basket_buylist");
//                conn3.addParamMap("id",CommonVar.loginInfo.getId());
//                conn3.onExcute((isResult3,data3)->{


//                });

//            });

        });
    }


}