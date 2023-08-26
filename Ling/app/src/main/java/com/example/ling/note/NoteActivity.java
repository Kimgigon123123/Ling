package com.example.ling.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ling.R;
import com.example.ling.capsule.CapsuleAddActivity;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityNoteBinding;
import com.example.ling.databinding.FragmentStoreCoBinding;
import com.example.ling.store.StoreCoAdater;
import com.example.ling.store.storeCO.StoreCOVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    String[] items = {"전체", "개인메모", "커플메모"};
    ActivityNoteBinding binding;

    String order = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        binding.spinner.setAdapter(adapter);


        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str = items[position];
                if (str.equals("전체")) {
                    all();
                } else if (str.equals("커플메모")) {
                    couple();
                } else if (str.equals("개인메모")) {
                    priv();
                }



            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        binding.btnAdd.setOnClickListener(v->{
            Intent intent = new Intent(this, NoteAddActivity.class);
            startActivity(intent);
        });



    }

    public void all(){
        CommonConn conn = new CommonConn(this, "select_note_all");
        conn.addParamMap("id",CommonVar.loginInfo.getId());
        conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
        conn.onExcute((isResult, data) -> {

            ArrayList<NoteVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<NoteVO>>() {
            }.getType());
            binding.tv.setVisibility(View.INVISIBLE);
            if (list.isEmpty()){
                binding.tv.setVisibility(View.VISIBLE);
            }
            binding.recvNote.setAdapter(new NoteAdapter(list,this));
            binding.recvNote.setLayoutManager(new GridLayoutManager(this,3));


        });
    }

    public void couple(){
        CommonConn conn = new CommonConn(this, "select_note_couple");
        conn.addParamMap("couple_num",CommonVar.loginInfo.getCouple_num());
        conn.onExcute((isResult, data) -> {

            ArrayList<NoteVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<NoteVO>>() {
            }.getType());

            binding.tv.setVisibility(View.INVISIBLE);
            if (list.isEmpty()){
                binding.tv.setVisibility(View.VISIBLE);
            }
            binding.recvNote.setAdapter(new NoteAdapter(list,this));
            binding.recvNote.setLayoutManager(new GridLayoutManager(this,3));


        });
    }

    public void priv(){
        CommonConn conn = new CommonConn(this, "select_note_private");
        conn.addParamMap("id",CommonVar.loginInfo.getId());
        conn.onExcute((isResult, data) -> {

            ArrayList<NoteVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<NoteVO>>() {
            }.getType());

            binding.tv.setVisibility(View.INVISIBLE);
            if (list.isEmpty()){
                binding.tv.setVisibility(View.VISIBLE);
            }
            binding.recvNote.setAdapter(new NoteAdapter(list,this));
            binding.recvNote.setLayoutManager(new GridLayoutManager(this,3));


        });
    }

    @Override
    public void onResume() {
        super.onResume();
        all();
    }

}