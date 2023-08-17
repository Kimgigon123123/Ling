package com.example.ling.date.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvFestivalBinding;
import com.example.ling.date.DateInfoVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FestivalItemAdapter extends RecyclerView.Adapter<FestivalItemAdapter.ViewHolder> {

    ItemRecvFestivalBinding binding;
    ArrayList<DateInfoVO> list;
    Context context;

    public FestivalItemAdapter(ArrayList<DateInfoVO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvFestivalBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        String imageUrl=list.get(i).getDate_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvFestival);
        h.binding.cvFestival.setOnClickListener(v -> {
            Intent intent = new Intent(context, FestDetailActivity.class);
            intent.putExtra("img", list.get(i).getDate_img());
            intent.putExtra("name", list.get(i).getDate_name());
            intent.putExtra("address", list.get(i).getDate_address());
            intent.putExtra("intro", list.get(i).getDate_intro());
            intent.putExtra("open", list.get(i).getOpen());
            intent.putExtra("end", list.get(i).getEnd());
            intent.putExtra("lan", list.get(i).getLan());
            intent.putExtra("lng", list.get(i).getLng());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvFestivalBinding binding;

        public ViewHolder(@NonNull ItemRecvFestivalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
