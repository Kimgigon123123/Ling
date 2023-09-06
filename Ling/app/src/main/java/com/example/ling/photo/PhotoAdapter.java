package com.example.ling.photo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ling.R;
import com.example.ling.databinding.ItemGridPhotoBinding;
import com.example.ling.databinding.ItemRecvScheduleBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{




    private ArrayList<PhotoVO> list;

    public PhotoAdapter(Context context, ArrayList<PhotoVO> list) {
        this.context = context;
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
            Glide.with(context).load(list.get(i).getPho_img()).into(h.binding.imgvPhoto1);

        h.binding.imgvPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 시 실행될 코드
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_image);

                ImageView dialogImageView = dialog.findViewById(R.id.imgv_dialog_view);
                Glide.with(context).load(list.get(i).getPho_img()).into(dialogImageView);


                ImageView closeImageView = dialog.findViewById(R.id.imgv_dialog_close);
                closeImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss(); // 다이얼로그 닫기
                    }
                });

                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.9);
                int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.9); // 높이는 내용에 맞게 조정 가능
                layoutParams.width = width;
                layoutParams.height = height;
                dialog.getWindow().setAttributes(layoutParams);


                dialog.show();
            }
        });
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
