package com.example.ling.board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityBoardNewContentBinding;
import com.example.ling.login.PreferenceManager;

public class Board_Write_ContentActivity extends AppCompatActivity {

    ActivityBoardNewContentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityBoardNewContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        // String menu = intent.getStringExtra("menu");
        if (getIntent().getStringExtra("type").equals("update")){
            BoardVO vo = (BoardVO) getIntent().getSerializableExtra("vo");
            binding.edtNewTitle.setText(vo.getTitle());
            binding.edtNewcontent.setText(vo.getContent());
            binding.btnNewcontentInsert.setOnClickListener(v -> {
                update(); // 수정 작업을 수행하는 메서드 호출
                onBackPressed();
            });
        }else{
            binding.btnNewcontentInsert.setOnClickListener(v->{
                insert();
                onBackPressed();
            });

        }


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

    public void update() {
        CommonConn conn = new CommonConn(this, "board.update");
        BoardVO vo = (BoardVO) getIntent().getSerializableExtra("vo");
        conn.addParamMap("id", vo.getId());
        conn.addParamMap("title", binding.edtNewTitle.getText().toString());
        conn.addParamMap("content", binding.edtNewcontent.getText().toString());

        conn.onExcute((isResult, data) -> {

        });
    }

}