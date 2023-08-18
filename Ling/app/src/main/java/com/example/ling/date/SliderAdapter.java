package com.example.ling.date;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.ling.databinding.FragmentDateBinding;
import com.example.ling.databinding.ItemSliderBinding;
import com.example.ling.date.festival.FestivalActivity;
import com.example.ling.date.restaurant.RestaurantActivity;
import com.example.ling.date.tour.TourActivity;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder>{

    Context context;
    ViewPager2 viewPager2;
    ArrayList<SliderVO> list;

    public SliderAdapter(Context context, ViewPager2 viewPager2, ArrayList<SliderVO> list) {
        this.context = context;
        this.viewPager2 = viewPager2;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSliderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgSlider.setImageResource(list.get(i).getImgRes());
        h.binding.sdText.setText(list.get(i).getName());
        h.binding.btn.setOnClickListener(v -> {
            if(list.get(i).getName().equals("전국 여행")) {
                Intent intent = new Intent(context, TourActivity.class);
                context.startActivity(intent);
            } else if (list.get(i).getName().equals("전국 맛집")) {
                Intent intent = new Intent(context, RestaurantActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, FestivalActivity.class);
                context.startActivity(intent);
            }
        });

        if (i == list.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemSliderBinding binding;

        public ViewHolder(@NonNull ItemSliderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(ArrayList<Integer> list) {
            try {
                Glide.with(context).load(list).into(binding.imgSlider);
            } catch (Exception e) {
                Log.d("error", "ERROR: " + e.getMessage());
            }
        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            list.addAll(list);
            notifyDataSetChanged();
        }
    };

}
