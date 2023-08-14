package com.example.ling.board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.databinding.ActivityJoinBinding;
import com.example.ling.databinding.ActivityNoticeContextBinding;
import com.example.ling.home.HomeFragment;

public class Notice_contextActivity extends AppCompatActivity {
    ActivityNoticeContextBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeContextBinding.inflate(getLayoutInflater());

        String board_no =  getIntent().getStringExtra("board_no");
        String board_cd =  getIntent().getStringExtra("board_cd");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, new ContentFragment(board_no, board_cd)).commit();
        setContentView(binding.getRoot());


    }
}