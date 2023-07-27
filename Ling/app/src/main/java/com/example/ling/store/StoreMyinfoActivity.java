package com.example.ling.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.ling.R;
import com.example.ling.databinding.ActivityStoreMyinfoBinding;

public class StoreMyinfoActivity extends AppCompatActivity {

    ActivityStoreMyinfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStoreMyinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recvZzim.setAdapter(new ZZimAdapter(this));
        binding.recvZzim.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        binding.recvBuylist.setAdapter(new BuylistAdapter(this));
        binding.recvBuylist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

    }
}