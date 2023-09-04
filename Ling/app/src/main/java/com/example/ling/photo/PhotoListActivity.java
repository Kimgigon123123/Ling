package com.example.ling.photo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityPhotoBinding;
import com.example.ling.databinding.ActivityPhotoListBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class PhotoListActivity extends AppCompatActivity {

    ActivityPhotoListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityPhotoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        select();

        binding.imgvFolderBack.setOnClickListener(v -> {
            finish();
        });

    }

    public void select(){
        CommonConn conn = new CommonConn(this, "photo_list");

       // PhotoVO vo = new PhotoVO();
        String folder_name = getIntent().getStringExtra("name");

        conn.addParamMap("folder_num", getIntent().getIntExtra("folder_no", -1));

        conn.onExcute((isResult, data) -> {

            binding.tvFolderName.setText(folder_name);

            ArrayList<PhotoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<PhotoVO>>(){}.getType());
            PhotoAdapter adapter = new PhotoAdapter(this, list);


            binding.recvPhotos.setAdapter(adapter);
            binding.recvPhotos.setLayoutManager(new GridLayoutManager(this, 3));

        });
    }
}