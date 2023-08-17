package com.example.ling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.ling.board.BoardCommentVO;
import com.example.ling.board.BoardReCommentVO;
import com.example.ling.board.Board_CommentAdapter;
import com.example.ling.board.Board_ReCommentAdapter;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityBoardNewContentBinding;
import com.example.ling.databinding.ActivityBoardReCommentBinding;
import com.example.ling.login.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Board_ReCommentActivity extends AppCompatActivity {
    ActivityBoardReCommentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityBoardReCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvBefore.setOnClickListener(v->{
            onBackPressed();
        });
        binding.tvCommentId.setText(getIntent().getStringExtra("tvCommentId"));
        binding.tvCommentWritedate.setText(getIntent().getStringExtra("tvCommentWritedate"));
        binding.tvCommentContent.setText(getIntent().getStringExtra("tvCommentContent"));
        recommentselect();
        binding.btnRegistComment.setOnClickListener(v->{
            regist();
        });
    }

    public void recommentselect(){
        int comment_id = getIntent().getIntExtra("id", -1);
        if(comment_id == -1) return;
        CommonConn conn = new CommonConn(this, "board.RecommentList");
        conn.addParamMap("comment_id" , comment_id);
        conn.onExcute((isResult, data) -> {
            ArrayList<BoardReCommentVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<BoardReCommentVO>>(){}.getType());
            Board_ReCommentAdapter adapter = new Board_ReCommentAdapter(list);
            binding.recvRecontent.setAdapter(adapter);
            binding.recvRecontent.setLayoutManager(new LinearLayoutManager(this));
        });
    }

    public void regist(){
        int comment_id =  getIntent().getIntExtra("id", -1);
        if(comment_id == -1) return;
        CommonConn conn = new CommonConn(this, "board.RecommentRegister");
        conn.addParamMap("content", binding.edtComment.getText().toString());
        conn.addParamMap("writer", PreferenceManager.getString(this,"id"));
        conn.addParamMap("comment_id" ,comment_id );

        conn.onExcute((isResult, data) -> {
            recommentselect();
            binding.edtComment.setText("");
        });
    }
}