package com.example.ling.board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityNoticeContextBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Board_DetailtActivity extends AppCompatActivity {
    ActivityNoticeContextBinding binding;
    MaterialDialog mAnimatedDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeContextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvBefore.setOnClickListener(v->{
            onBackPressed();
        });

        binding.btnDelete.setOnClickListener(v->{

                mAnimatedDialog = new MaterialDialog.Builder(this)
                        .setTitle("삭제")
                        .setMessage("이 글을 정말 삭제하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("삭제", R.drawable.ic_delete, new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(com.example.ling.board.interfaces.DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "삭제완료!", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                                //delete();
                                finish();
                            }
                        })
                        .setNegativeButton("취소", R.drawable.ic_close, new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(com.example.ling.board.interfaces.DialogInterface dialogInterface, int which) {
                                Toast.makeText(getApplicationContext(), "취소했습니다.", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setAnimation("delete_anim.json")
                        .build();

            mAnimatedDialog.show();
                //mAnimatedDialog.setOnClickListener(this);
            //delete();
        });


    }



    public void UserContentselect() {
        CommonConn conn = new CommonConn(this, "board.usercontent");
        String board_no =  getIntent().getStringExtra("board_no");
        String board_cd =  getIntent().getStringExtra("board_cd");
        conn.addParamMap("id", board_no);
        conn.addParamMap("board_cd", board_cd);
        conn.onExcute((isResult, data) -> {
            BoardVO boardvo = new Gson().fromJson(data, new TypeToken<BoardVO>() {
            }.getType());

            binding.btnUpdate.setOnClickListener(v->{
                Intent intent = new Intent(this, Board_Write_ContentActivity.class);
                intent.putExtra("vo",boardvo);
                intent.putExtra("type", "update");
                startActivity(intent);
            });

            Board_User_ContentAdapter adapter = new Board_User_ContentAdapter(boardvo);
            binding.recvContent.setAdapter(adapter);
            binding.recvContent.setLayoutManager(new LinearLayoutManager(this));

            if(CommonVar.loginInfo.getId().equals("admin")||boardvo.getWriter().equals(CommonVar.loginInfo.getId())){
                binding.lnButton.setVisibility(View.VISIBLE);
            }else{
                binding.lnButton.setVisibility(View.INVISIBLE);

            }

        });
    }
    public void delete() {
        CommonConn conn = new CommonConn(this, "board.delete");
        String board_no =  getIntent().getStringExtra("board_no");
        conn.addParamMap("id", board_no);
        conn.onExcute((isResult, data) -> {

        });
    }
    @Override
    public void onStart() {
        super.onStart();
//        binding.swipeLayout.setOnRefreshListener(()->{
//
//            binding.swipeLayout.setRefreshing(false);
//        });
        UserContentselect();
    }
}