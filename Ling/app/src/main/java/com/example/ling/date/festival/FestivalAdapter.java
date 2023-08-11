package com.example.ling.date.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ActivityFestDetailBinding;
import com.example.ling.databinding.ItemRecvFestactBinding;
import com.example.ling.databinding.ItemRecvTouractBinding;
import com.example.ling.date.DateInfoVO;
import com.example.ling.date.tour.TourDetailActivity;

import java.util.ArrayList;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.ViewHolder> {

    Context context;
    ArrayList<DateInfoVO> list;

    public FestivalAdapter(Context context, ArrayList<DateInfoVO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecvFestactBinding binding=null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvFestactBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvFestival.setImageResource(R.drawable.ic_launcher_background);
        //h.binding.imgvFav2.setVisibility(View.INVISIBLE);
        h.binding.tvFname.setText(list.get(i).getDate_name());
        h.binding.tvFaddr.setText(list.get(i).getDate_address());
        h.binding.imgvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((boolean) h.binding.imgvFav.getTag()){
                    h.binding.imgvFav.setTag(false);
                    h.binding.imgvFav.setImageResource(R.drawable.ic_fav);
                }else{
                    h.binding.imgvFav.setTag(true);
                    h.binding.imgvFav.setImageResource(R.drawable.ic_fav2);
                }
            }
        });
        h.binding.lnFestival.setOnClickListener(v -> {
            Intent intent = new Intent(context, FestDetailActivity.class);
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
        ItemRecvFestactBinding binding;

        public ViewHolder(@NonNull ItemRecvFestactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.imgvFav.setTag(false);
        }
    }

}
