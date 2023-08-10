package com.example.ling.date.tour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ItemRecvTouractBinding;
import com.example.ling.date.DateDibsVO;
import com.example.ling.date.DateInfoVO;
import com.google.android.gms.common.internal.service.Common;

import java.util.ArrayList;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {

    Context context;
    ArrayList<DateInfoVO> list;

    public TourAdapter(Context context, ArrayList<DateInfoVO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecvTouractBinding binding = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvTouractBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvTour.setImageResource(R.drawable.ic_launcher_background);
        //h.binding.imgvFav2.setVisibility(View.INVISIBLE);
        h.binding.tvTname.setText(list.get(i).getDate_name());
        h.binding.tvTaddr.setText(list.get(i).getDate_address());
        h.binding.imgvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((boolean) h.binding.imgvFav.getTag()) {
                    h.binding.imgvFav.setTag(false);
                    h.binding.imgvFav.setImageResource(R.drawable.ic_fav);
                } else {
                    h.binding.imgvFav.setTag(true);
                    h.binding.imgvFav.setImageResource(R.drawable.ic_fav2);
                    CommonConn conn = new CommonConn(context, "date_insertdibs");
                    conn.addParamMap("date_id", list.get(i).getDate_id());
                    conn.addParamMap("date_category_code", list.get(i).getDate_category_code());
                    conn.onExcute((isResult, data) -> {
                    });
                }
            }
        });
        h.binding.lnTour.setOnClickListener(v -> {
            Intent intent = new Intent(context, TourDetailActivity.class);
            intent.putExtra("img", R.drawable.ic_launcher_background);
            intent.putExtra("name", list.get(i).getDate_name());
            intent.putExtra("address", list.get(i).getDate_address());
            intent.putExtra("intro", list.get(i).getDate_intro());
            intent.putExtra("open", list.get(i).getOpen());
            intent.putExtra("end", list.get(i).getEnd());
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
        ItemRecvTouractBinding binding;

        public ViewHolder(@NonNull ItemRecvTouractBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.imgvFav.setTag(false);
        }
    }
}
