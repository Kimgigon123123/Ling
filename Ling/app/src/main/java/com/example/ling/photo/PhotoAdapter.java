package com.example.ling.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ling.R;
import com.example.ling.calendar.CalendarAdapter;
import com.example.ling.databinding.ItemGridPhotoBinding;
import com.example.ling.databinding.ItemRecvScheduleBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{




    private ArrayList<PhotoVO> list;

    public PhotoAdapter(Context context, ArrayList<PhotoVO> list) {

        this.list = list;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemGridPhotoBinding binding = ItemGridPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
            h.binding.imgvPhoto1.setImageResource(Integer.parseInt(list.get(i).getPho_img()));
            h.binding.tvTest.setText(list.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemGridPhotoBinding binding;
        public ViewHolder(@NonNull ItemGridPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
