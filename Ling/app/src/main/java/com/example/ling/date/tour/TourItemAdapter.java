package com.example.ling.date.tour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvTourBinding;
import com.example.ling.date.DateInfoVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TourItemAdapter extends RecyclerView.Adapter<TourItemAdapter.ViewHolder> {

    ItemRecvTourBinding binding;
    ArrayList<DateInfoVO> list;
    Context context;

    public TourItemAdapter(ArrayList<DateInfoVO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvTourBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        String imageUrl=list.get(i).getDate_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvTour);
        h.binding.cvTour.setOnClickListener(v -> {
            Intent intent = new Intent(context, TourDetailActivity.class);
            intent.putExtra("img", list.get(i).getDate_img());
            intent.putExtra("name", list.get(i).getDate_name());
            intent.putExtra("address", list.get(i).getDate_address());
            intent.putExtra("intro", list.get(i).getDate_intro());
            intent.putExtra("open", list.get(i).getOpen());
            intent.putExtra("end", list.get(i).getEnd());
            intent.putExtra("lan", list.get(i).getLan());
            intent.putExtra("lng", list.get(i).getLng());
            intent.putExtra("date_id", list.get(i).getDate_id());
            intent.putExtra("date_category_code", list.get(i).getDate_category_code());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvTourBinding binding;

        public ViewHolder(@NonNull ItemRecvTourBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
