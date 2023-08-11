package com.example.ling.board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.ling.R;
import com.example.ling.databinding.ActivityBoardNewContentBinding;
import com.example.ling.databinding.ActivityStoreMyinfoBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.events.EventHandler;

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

    }

}