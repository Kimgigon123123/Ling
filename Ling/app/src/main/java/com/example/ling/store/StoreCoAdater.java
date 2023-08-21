package com.example.ling.store;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvDibsBinding;
import com.example.ling.databinding.ItemRecvStoreCoBinding;
import com.example.ling.store.myinfo.StoreZzimListVO;
import com.example.ling.store.myinfo.ZZimAdapter;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class StoreCoAdater extends RecyclerView.Adapter<StoreCoAdater.ViewHolder> {

    ItemRecvStoreCoBinding binding;
    Context context;

    ArrayList<StoreCOVO> list;



    public StoreCoAdater(Context context) {
        this.context = context;
    }

    public StoreCoAdater(ArrayList<StoreCOVO> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvStoreCoBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

        h.binding.imgvItem.setImageResource(R.drawable.ic_launcher_background);
        h.binding.tvName.setText(list.get(i).getItem_name());
        h.binding.tvPrice.setText(list.get(i).getItem_price()+"원");
        h.binding.btnAdd.setText(list.get(i).getZzim_product());
//        if(zzimlist.get(num).getItem_code().equals(list.get(i).getItem_code())){
//                    h.binding.btnAdd.setText("♥");
//                    notifyDataSetChanged();
//       }

//        CommonConn conn2 = new CommonConn(context, "store_list_zzim");
//        conn2.addParamMap("id", CommonVar.loginInfo.getId());
//        conn2.onExcute((isResult, data) -> {
//
//            ArrayList<StoreZzimListVO> zzimlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreZzimListVO>>() {
//            }.getType());
//
//            for(int num=0 ;num< zzimlist.size();num++){
//                if(zzimlist.get(num).getItem_code().equals(list.get(i).getItem_code())){
//                    h.binding.btnAdd.setText("♥");
//                    notifyDataSetChanged();
//                }
//            }
//
//        });


        h.binding.btnAdd.setOnClickListener(v->{
            if (h.binding.btnAdd.getText().equals("♡")){
                CommonConn conn = new CommonConn(context, "store_insert_zzim");
                conn.addParamMap("item_code", list.get(i).getItem_code());
                conn.addParamMap("category_code", list.get(i).getCategory_code());
                conn.addParamMap("id", CommonVar.loginInfo.getId());
                conn.onExcute((isResult, data) -> {
                    h.binding.btnAdd.setText("♥");
//                if (!isResult) {
//
//                } else {
//                    // 매핑 연결에 성공한 경우
//
//                    if(data==null){
//                        h.binding.btnAdd.setText("♡");
//                        CommonConn conn3 = new CommonConn(context , "store_delete_zzim");
//                        conn3.addParamMap("item_code" , list.get(i).getItem_code());
//
//                        conn3.onExcute((isResult3, data3) -> {
//                        });
//                    }else{
//                        h.binding.btnAdd.setText("♥");
//                        Toast.makeText(context, "찜목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
                });

            }else if(h.binding.btnAdd.getText().equals("♥")){
                CommonConn conn3 = new CommonConn(context , "store_delete_zzim");
                conn3.addParamMap("item_code" , list.get(i).getItem_code());
                conn3.onExcute((isResult3, data3) -> {
                    h.binding.btnAdd.setText("♡");
                });
            }
        });

        String imageUrl=list.get(i).getItem_img();
                                 Picasso.get()
                                .load(imageUrl)
                                .into(h.binding.imgvItem);

        h.binding.intoItem.setOnClickListener(v -> {
            Intent intent = new Intent(context,StorePurchaseActivity.class);
            intent.putExtra("name",list.get(i).getItem_name());
            intent.putExtra("content",list.get(i).getItem_content());
            intent.putExtra("price",list.get(i).getItem_price());
            intent.putExtra("item_code",list.get(i).getItem_code());
            intent.putExtra("item_img",list.get(i).getItem_img());
            intent.putExtra("category_code",list.get(i).getCategory_code());

                    CommonConn conn = new CommonConn(context,"store_popular_up");
                    conn.addParamMap("item_code" ,list.get(i).getItem_code());

                    conn.onExcute((isResult, data) -> {


                    });




            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreCoBinding binding;

        public ViewHolder(@NonNull ItemRecvStoreCoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}
