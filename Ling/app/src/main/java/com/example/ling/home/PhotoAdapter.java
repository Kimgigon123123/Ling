package com.example.ling.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemGridPhotoBinding;
import com.example.ling.photo.PhotoVO;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{


    public PhotoAdapter(ArrayList<PhotoVO> list, Context context) {
        this.list = list;
    }

    ArrayList<PhotoVO> list;


    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {


            super(itemView);
        }
    }
}
