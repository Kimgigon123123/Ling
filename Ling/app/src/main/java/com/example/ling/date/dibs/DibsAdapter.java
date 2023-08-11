package com.example.ling.date.dibs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvDibsBinding;
import com.example.ling.date.DateDibsVO;
import com.example.ling.date.tour.TourDetailActivity;

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
        h.binding.imgvDibs.setImageResource(R.drawable.ic_launcher_background);
        h.binding.tvName.setText(list.get(i).getDate_name());
        h.binding.tvAddr.setText(list.get(i).getDate_address());
        h.binding.btnDelete.setOnClickListener(v -> {
            CommonConn conn = new CommonConn(context, "date_deletedibs");
            conn.addParamMap("dibs_id", list.get(i).getDibs_id());
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.onExcute((isResult, data) -> {
                list.remove(i);
                notifyDataSetChanged();
            });
            Toast.makeText(context, "목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        });
        h.binding.lnDibs.setOnClickListener(v -> {
            Intent intent = new Intent(context, DibsDetailActivity.class);
            if(list.get(i).getDate_category_code().equals("TO") || list.get(i).getDate_category_code().equals("RE")) {
                intent.putExtra("img", R.drawable.ic_launcher_background);
                intent.putExtra("name", list.get(i).getDate_name());
                intent.putExtra("address", list.get(i).getDate_address());
                intent.putExtra("intro", list.get(i).getDate_intro());
                intent.putExtra("code", list.get(i).getDate_category_code());
                intent.putExtra("lan", list.get(i).getLan());
                intent.putExtra("lng", list.get(i).getLng());
            } else if(list.get(i).getDate_category_code().equals("FE")) {
                intent.putExtra("img", R.drawable.ic_launcher_background);
                intent.putExtra("name", list.get(i).getDate_name());
                intent.putExtra("address", list.get(i).getDate_address());
                intent.putExtra("intro", list.get(i).getDate_intro());
                intent.putExtra("open", list.get(i).getOpen());
                intent.putExtra("end", list.get(i).getEnd());
                intent.putExtra("code", list.get(i).getDate_category_code());
                intent.putExtra("lan", list.get(i).getLan());
                intent.putExtra("lng", list.get(i).getLng());
            }
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