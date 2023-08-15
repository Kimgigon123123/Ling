package com.example.ling.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemGridPhotoBinding;
import com.example.ling.databinding.ItemRecvFolderBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder>{

    public FolderAdapter(ArrayList<FolderVO> list) {
        this.list = list;
    }

    ArrayList<FolderVO> list;

    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvFolderBinding binding = ItemRecvFolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FolderAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
                h.binding.tvFolderTitle.setText(list.get(i).getFolder_name());
        String directoryPath = "D:\\WorkSpace\\Ling\\image\\photo\\all"; // 이미지 파일이 있는 디렉토리 경로

        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null && files.length > 0) {
                Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified())); // 가장 최근에 수정된 파일순으로 정렬

                File lastImageFile = files[0];
                if (lastImageFile.isFile()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(lastImageFile.getAbsolutePath());
                    h.binding.imgvFolder.setImageBitmap(bitmap);
                }
            } else {
                // 디렉토리가 비어있는 경우 처리
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvFolderBinding binding;

        public ViewHolder(@NonNull ItemRecvFolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
