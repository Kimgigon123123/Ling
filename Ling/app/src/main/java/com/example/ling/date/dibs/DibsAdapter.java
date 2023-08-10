package com.example.ling.date.dibs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
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
        h.binding.imgvFav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((boolean) h.binding.imgvFav2.getTag()) {
                    CommonConn conn = new CommonConn(context, "date_deletedibs");
                    conn.addParamMap("dibs_id", list.get(i).getDibs_id());
                    conn.addParamMap("id", list.get(i).getId());
                    conn.onExcute((isResult, data) -> {
                        list.remove(i);
                        notifyDataSetChanged();
                    });
                }
            }
        });
        h.binding.lnDibs.setOnClickListener(v -> {
            Intent intent = new Intent(context, DibsDetailActivity.class);
            context.startActivity(intent);
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

        ItemRecvDibsBinding binding;

        public ViewHolder(@NonNull ItemRecvDibsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.imgvFav2.setTag(true);
        }
    }
}