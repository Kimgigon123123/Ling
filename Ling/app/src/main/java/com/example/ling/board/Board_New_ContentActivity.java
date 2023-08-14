package com.example.ling.board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityBoardNewContentBinding;
import com.example.ling.databinding.ActivityStoreMyinfoBinding;
import com.example.ling.login.PreferenceManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.events.EventHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Board_New_ContentActivity extends AppCompatActivity {

    ActivityBoardNewContentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityBoardNewContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String menu = intent.getStringExtra("menu");

        binding.btnNewcontentInsert.setOnClickListener(v->{
            insert();

            onBackPressed();
        });
    }

    public void insert(){
        CommonConn conn = new CommonConn(this, "board.insert");
        conn.addParamMap("board_cd" , getIntent().getStringExtra("menu"));
        conn.addParamMap("title", binding.edtNewTitle.getText().toString());
        conn.addParamMap("content", binding.edtNewcontent.getText().toString());
        conn.addParamMap("writer", PreferenceManager.getString(this,"id"));
        conn.onExcute((isResult, data) -> {

        });
    }

}