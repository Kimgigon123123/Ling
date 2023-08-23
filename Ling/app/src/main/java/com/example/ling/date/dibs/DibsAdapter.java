package com.example.ling.date.dibs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvDibsBinding;
import com.example.ling.date.list.DateDetailActivity;
import com.example.ling.date.list.DateInfoVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DibsAdapter extends RecyclerView.Adapter<DibsAdapter.ViewHolder> {

    Context context;
    ArrayList<DateDibsVO> list;

    public DibsAdapter(Context context, ArrayList<DateDibsVO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecvDibsBinding binding;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvDibsBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        String imageUrl=list.get(i).getDate_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvDibs);
        h.binding.tvName.setText(list.get(i).getDate_name());
        h.binding.tvAddr.setText(list.get(i).getDate_address());
        h.binding.btnDelete.setOnClickListener(v -> {
            CommonConn conn = new CommonConn(context, "date_deletedibs");
            conn.addParamMap("date_id", list.get(i).getDate_id());
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.onExcute((isResult, data) -> {
                list.remove(i);
                notifyDataSetChanged();
            });
            Toast.makeText(context, "목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        });
        h.binding.lnDibs.setOnClickListener(v -> {
            Intent intent = new Intent(context, DateDetailActivity.class);
            DateInfoVO vo = new DateInfoVO();
            vo.setDate_img(list.get(i).getDate_img());
            vo.setDate_category_code(list.get(i).getDate_category_code());
            vo.setOpen(list.get(i).getOpen());
            vo.setEnd(list.get(i).getEnd());
            vo.setTel(list.get(i).getTel());
            vo.setDate_address(list.get(i).getDate_address());
            vo.setDate_intro(list.get(i).getDate_intro());
            vo.setDate_id(list.get(i).getDate_id());
            vo.setLng(list.get(i).getLng());
            vo.setLan(list.get(i).getLan());


            intent.putExtra("vo", list.get(i));
            context.startActivity(intent);
        });
        // if 문 카테고리 별로 intent putextra 다르게
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

        ItemRecvDibsBinding binding;

        public ViewHolder(@NonNull ItemRecvDibsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}