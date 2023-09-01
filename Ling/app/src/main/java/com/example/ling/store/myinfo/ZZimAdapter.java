package com.example.ling.store.myinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

                 h.binding.tvName.setText(list.get(i).item_name);

        h.binding.imgvItem.setOnClickListener(v -> {
            Intent intent = new Intent(context,StorePurchaseActivity.class);
            intent.putExtra("name",list.get(i).getItem_name());
            intent.putExtra("content",list.get(i).getItem_content());
            intent.putExtra("price",list.get(i).getItem_price());
            intent.putExtra("item_code",list.get(i).getItem_code());
            intent.putExtra("item_img",list.get(i).getItem_img());
            intent.putExtra("category_code",list.get(i).getCategory_code());


            context.startActivity(intent);
        });

        h.binding.imgvCancel.setOnClickListener(v -> {
            // 사용자에게 확인을 묻는 다이얼로그 표시
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setMessage("정말로 삭제하시겠습니까?");

            // "확인" 버튼을 눌렀을 때
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CommonConn conn = new CommonConn(context , "store_delete_zzim");
                    conn.addParamMap("item_code" , list.get(i).getItem_code());

                    conn.onExcute((isResult, data) -> {
                        list.remove(i);
                        notifyDataSetChanged();
                    });

                    Toast.makeText(context, "찜목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            // "취소" 버튼을 눌렀을 때
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 아무런 작업을 하지 않고 다이얼로그를 닫습니다.
                    dialog.dismiss();
                }
            });

            // 다이얼로그 표시
            AlertDialog dialog = builder.create();
            dialog.show();
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
