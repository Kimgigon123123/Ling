package com.example.ling.date.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvDateListBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DateListItemAdapter extends RecyclerView.Adapter<DateListItemAdapter.ViewHolder> {

    Context context;
    ArrayList<DateInfoVO> list;

    public DateListItemAdapter(Context context, ArrayList<DateInfoVO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecvDateListBinding binding=null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvDateListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        String imageUrl=list.get(i).getDate_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvFestival);
        //h.binding.imgvFav2.setVisibility(View.INVISIBLE);
        h.binding.tvFname.setText(list.get(i).getDate_name());
        h.binding.tvFaddr.setText(list.get(i).getDate_address());
        h.binding.btnAdd.setText(list.get(i).getDibs());
        h.binding.btnAdd.setOnClickListener(v -> {
            if (h.binding.btnAdd.getText().equals("♡")) {
                changeDibs("date_insertdibs" , i);
                h.binding.btnAdd.setText("♥");
                Toast.makeText(context, "관심목록에 등록되었습니다.", Toast.LENGTH_SHORT).show();

            } else if(h.binding.btnAdd.getText().equals("♥")) {
                changeDibs("date_deletedibs" , i);
                h.binding.btnAdd.setText("♡");
                Toast.makeText(context, "관심목록에서 제거되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        h.binding.lnFestival.setOnClickListener(v -> {
            Intent intent = new Intent(context, DateDetailActivity.class);
            intent.putExtra("vo", list.get(i));
//            intent.putExtra("img", list.get(i).getDate_img());
//            intent.putExtra("name", list.get(i).getDate_name());
//            intent.putExtra("address", list.get(i).getDate_address());
//            intent.putExtra("intro", list.get(i).getDate_intro());
//            intent.putExtra("open", list.get(i).getOpen());
//            intent.putExtra("end", list.get(i).getEnd());
//            intent.putExtra("lan", list.get(i).getLan());
//            intent.putExtra("lng", list.get(i).getLng());
//            intent.putExtra("date_id", list.get(i).getDate_id());
//            intent.putExtra("date_category_code", list.get(i).getDate_category_code());
            context.startActivity(intent);
        });
    }

    public void changeDibs(String mapping , int idx){
        CommonConn conn = new CommonConn(context, mapping);
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.addParamMap("date_id", list.get(idx).getDate_id());
        conn.addParamMap("date_category_code", list.get(idx).getDate_category_code());
        conn.onExcute((isResult, data) -> {


        });
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecvDateListBinding binding;

        public ViewHolder(@NonNull ItemRecvDateListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
