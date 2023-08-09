package com.example.ling.store.myinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ItemRecvStoreMyinfoZzimBinding;
import com.example.ling.store.StorePurchaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ZZimAdapter extends RecyclerView.Adapter<ZZimAdapter.ViewHolder> {

    ItemRecvStoreMyinfoZzimBinding binding;

    ArrayList<StoreZzimListVO> list;

    Context context;

    public ZZimAdapter(ArrayList<StoreZzimListVO> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvStoreMyinfoZzimBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {


        String imageUrl =list.get(i).item_img;
                 Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvItem);

        h.binding.imgvItem.setOnClickListener(v -> {
            Intent intent = new Intent(context,StorePurchaseActivity.class);
            intent.putExtra("name",list.get(i).getItem_name());
            intent.putExtra("content",list.get(i).getItem_content());
            intent.putExtra("price",list.get(i).getItem_price());
            intent.putExtra("item_code",list.get(i).getItem_code());
            intent.putExtra("item_img",list.get(i).getItem_img());


            context.startActivity(intent);
        });

        h.binding.imgvCancle.setOnClickListener(v->{
            CommonConn conn = new CommonConn(context , "store_delete_zzim");
            conn.addParamMap("item_code" , list.get(i).getItem_code());

            conn.onExcute((isResult, data) -> {
                list.remove(i);
                notifyDataSetChanged();
            });



            Toast.makeText(context, "찜목록에서 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvStoreMyinfoZzimBinding binding;


        public ViewHolder(@NonNull ItemRecvStoreMyinfoZzimBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
